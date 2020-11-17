package se.sogeti.webscraperapi.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import org.bson.types.ObjectId;
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

    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
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

    public ResponseEntity<Advert> createAdvert(AdvertResponseObj advertResponseObj) {
        Advert newAdvert = advertResponseObj.build();

        log.info("{}", newAdvert);
        log.info("======================================================================================");
        log.info("{}", advertResponseObj);

        saveImages(advertResponseObj.getImages(), advertResponseObj.getItemId());

        try {
            return ResponseEntity.ok(advertRepository.save(newAdvert));
        } catch (DuplicateKeyException e) {
            log.error("Duplicate key at Advert!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Advert());
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