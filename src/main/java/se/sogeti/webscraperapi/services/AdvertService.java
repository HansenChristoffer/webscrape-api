package se.sogeti.webscraperapi.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
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

    public Advert findByName(String name) {
        return advertRepository.findByName(name).orElseThrow(() -> new AbstractNotFoundException(name));
    }

    public Advert findByHref(String href) {
        return advertRepository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
    }

    public Advert findByObjectNumber(String objectNumber) {
        return advertRepository.findByObjectNumber(objectNumber)
                .orElseThrow(() -> new AbstractNotFoundException(objectNumber));
    }

    public ResponseEntity<Advert> createAdvert(AdvertResponseObj advertResponseObj) {
        Advert newAdvert = advertResponseObj.build();
        newAdvert.setAddedDate(Instant.now());

        saveImages(advertResponseObj.getImages(), advertResponseObj.getAdvertPageImage(),
                advertResponseObj.getObjectNumber());

        try {
            return ResponseEntity.ok(advertRepository.save(newAdvert));
        } catch (DuplicateKeyException e) {
            log.error("Duplicate key at Advert!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Advert());
    }

    public ResponseEntity<Advert> replaceAdvert(Advert newAdvert, String id) {
        return advertRepository.findByObjectId(new ObjectId(id)).map(advert -> {
            advert.setName(newAdvert.getName());
            advert.setCategoryName(newAdvert.getCategoryName());
            advert.setDescription(newAdvert.getDescription());
            advert.setHref(newAdvert.getHref());
            advert.setObjectNumber(newAdvert.getObjectNumber());
            advert.setPrice(newAdvert.getPrice());
            advert.setPublished(newAdvert.getPublished());
            advert.setSellerName(newAdvert.getSellerName());
            advert.setImage(newAdvert.getImage());
            return ResponseEntity.ok(advertRepository.save(advert));
        }).orElseGet(() -> {
            newAdvert.setId(id);
            return ResponseEntity.ok(advertRepository.save(newAdvert));
        });
    }

    public void deleteAll() {
        advertRepository.deleteAll();
    }

    private void saveImages(List<byte[]> images, byte[] advertPageImage, String objectNumber) {
        images.forEach(img -> {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(img);) {
                BufferedImage bImg = ImageIO.read(bis);
                ImageIO.write(bImg, "jpg", new File("src/main/resources/images/".concat(objectNumber).concat("-")
                        .concat(String.valueOf(images.indexOf(img)))));
            } catch (IOException ioe) {
                log.error("saveImages().IOException[0] == {}", ioe.getMessage());
            }
        });

        try (ByteArrayInputStream bis = new ByteArrayInputStream(advertPageImage);) {
            BufferedImage bImg = ImageIO.read(bis);
            ImageIO.write(bImg, "jpg",
                    new File("src/main/resources/images/".concat(objectNumber).concat("-").concat("advertPageImage")));
        } catch (IOException ioe) {
            log.error("saveImages().IOException[1] == {}", ioe.getMessage());
        }

    }
}