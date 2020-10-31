package se.sogeti.webscraperapi.services;

import java.time.Instant;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
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

    public ResponseEntity<Advert> createAdvert(Advert newAdvert) {
        newAdvert.setAddedDate(Instant.now());

        try {
            return ResponseEntity.ok(advertRepository.save(newAdvert));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Advert!");
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
}