package se.sogeti.webscraperapi.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.AdvertResponseObj;
import se.sogeti.webscraperapi.repositories.AdvertRepository;

@Service
@Slf4j
public class AdvertService {

    private AdvertRepository advertRepository;
    private ModelMapper modelMapper;

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
        this.modelMapper = new ModelMapper();
        TypeMap<AdvertResponseObj, Advert> typeMap = modelMapper.createTypeMap(AdvertResponseObj.class, Advert.class);

        typeMap.addMappings(mapper -> {
            mapper.map(src -> src.getItemId(), Advert::setItemId);
            mapper.map(src -> src.getBrands(), Advert::setBrands);
            mapper.map(src -> src.isAuction(), Advert::setAuction);
            mapper.map(src -> src.getCanonicalURL(), Advert::setCanonicalURL);
            mapper.map(src -> src.getAllowedBuyerRegion(), Advert::setAllowedBuyerRegion);
            mapper.map(src -> src.getCategoryId(), Advert::setCategoryId);
            mapper.map(src -> src.getColors(), Advert::setColors);
            mapper.map(src -> src.getCondition(), Advert::setCondition);
            mapper.map(src -> src.getMemberId(), Advert::setMemberId);
            mapper.map(src -> src.getDescription(), Advert::setDescription);
            mapper.map(src -> src.getEndDate(), Advert::setEndDate);
            mapper.map(src -> src.getOpeningBid(), Advert::setOpeningBid);
            mapper.map(src -> src.getShipsToBuyer(), Advert::setShipsToBuyer);
            mapper.map(src -> src.getSizes(), Advert::setSizes);
            mapper.map(src -> src.getStartDate(), Advert::setStartDate);
            mapper.map(src -> src.getTitle(), Advert::setTitle);
        });
    }

    public Advert findByObjectId(String id) {
        return advertRepository.findByObjectId(new ObjectId(id)).orElseThrow(() -> new AbstractNotFoundException(id));
    }

    public Collection<Advert> findAll() {
        return advertRepository.findAll();
    }

    public Advert findByTitle(String title) {
        return advertRepository.findByTitle(title).orElseThrow(() -> new AbstractNotFoundException(title));
    }

    public Advert findByCanonicalURL(String canonicalURL) {
        return advertRepository.findByCanonicalURL(canonicalURL)
                .orElseThrow(() -> new AbstractNotFoundException(canonicalURL));
    }

    public Advert findByItemId(Integer itemId) {
        return advertRepository.findByItemId(itemId).orElseThrow(() -> new AbstractNotFoundException(itemId));
    }

    public ResponseEntity<AdvertResponseObj> createAdvert(AdvertResponseObj advertResponseObj) {
        saveImages(advertResponseObj.getImages(), advertResponseObj.getItemId());

        try {
            Advert newAdvert = modelMapper.map(advertResponseObj, Advert.class);
            Advert savedAdvert = advertRepository.save(newAdvert);
            return ResponseEntity.ok(modelMapper.map(savedAdvert, AdvertResponseObj.class));
        } catch (DuplicateKeyException e) {
            log.error("Duplicate key at Advert!");
        } catch (Exception e) {
            log.error("createAdvert().Exception == {}", e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new AdvertResponseObj());
    }

    public ResponseEntity<Advert> replaceAdvert(Advert newAdvert, String id) {
        return advertRepository.findByObjectId(new ObjectId(id)).map(advert -> {
            advert.setTitle(newAdvert.getTitle());
            advert.setDescription(newAdvert.getDescription());
            advert.setAuction(newAdvert.isAuction());
            advert.setAllowedBuyerRegion(newAdvert.getAllowedBuyerRegion());
            advert.setCanonicalURL(newAdvert.getCanonicalURL());
            advert.setCategoryId(newAdvert.getCategoryId());
            advert.setCondition(newAdvert.getCondition());
            advert.setColors(newAdvert.getColors());
            advert.setImages(newAdvert.getImages());
            advert.setItemId(newAdvert.getItemId());
            advert.setBrands(newAdvert.getBrands());
            advert.setMemberId(newAdvert.getMemberId());
            advert.setOpeningBid(newAdvert.getOpeningBid());
            advert.setShipsToBuyer(newAdvert.getShipsToBuyer());
            advert.setSizes(newAdvert.getSizes());
            return ResponseEntity.ok(advertRepository.save(advert));
        }).orElseGet(() -> {
            return ResponseEntity.ok(advertRepository.save(newAdvert));
        });

    }

    public void deleteAll() {
        advertRepository.deleteAll();
    }

    private void saveImages(List<byte[]> images, Integer itemId) {
        images.forEach(img -> {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(img);) {
                BufferedImage bImg = ImageIO.read(bis);
                ImageIO.write(bImg, "jpg",
                        new File("src/main/resources/images/tradera/adverts".concat(String.valueOf(itemId)).concat("-")
                                .concat(String.valueOf(images.indexOf(img)).concat(".jpg"))));
            } catch (IOException ioe) {
                log.error("saveImages().IOException == {}", ioe.getMessage());
            }
        });
    }
}