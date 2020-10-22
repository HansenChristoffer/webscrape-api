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
import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.repositories.SellerRepository;

@Service
@Slf4j
public class SellerService {

    private final SellerRepository sellerRepository;

    SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public Seller findByObjectId(String id) {
        return sellerRepository.findByObjectId(new ObjectId(id))
                .orElseThrow(() -> new AbstractNotFoundException(id));
    }

    public Collection<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller findByName(String name) {
        return sellerRepository.findByName(name) //
                .orElseThrow(() -> new AbstractNotFoundException(name));
    }

    public Seller findByHref(String href) {
        return sellerRepository.findByHref(href) //
                .orElseThrow(() -> new AbstractNotFoundException(href));
    }

    public ResponseEntity<Seller> createSeller(Seller newSeller) {
        newSeller.setAddedDate(Instant.now());

        try {
            return ResponseEntity.ok(sellerRepository.save(newSeller));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Seller!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Seller());
    }

    public ResponseEntity<Seller> replaceSeller(Seller newSeller, String id) {

        return sellerRepository.findByObjectId(new ObjectId(id)) //
                .map(seller -> {
                    seller.setName(newSeller.getName());
                    seller.setLocation(newSeller.getLocation());
                    seller.setRegistered(newSeller.getRegistered());
                    seller.setHref(newSeller.getHref());
                    return ResponseEntity.ok(sellerRepository.save(seller));

                }).orElseGet(() -> {
                    newSeller.setId(id);
                    return ResponseEntity.ok(sellerRepository.save(newSeller));
                });
    }

    public void deleteAll() {
        sellerRepository.deleteAll();
    }
}
