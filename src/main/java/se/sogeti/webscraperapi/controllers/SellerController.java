package se.sogeti.webscraperapi.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.services.SellerService;

@RestController
@RequestMapping(value = "/api")
public class SellerController {

    private SellerService service;

    SellerController(SellerService service) {
        this.service = service;
    }

    @GetMapping(value = "/sellers/id")
    public Seller findByObjectId(@RequestParam String value) {
        return service.findByObjectId(value);
    }

    @GetMapping(value = "/sellers")
    public Collection<Seller> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/sellers/alias")
    public Seller findByAlias(@RequestParam String value) {
        return service.findByAlias(value);
    }

    @GetMapping(value = "/sellers/url")
    public Seller findByCanonicalURL(@RequestParam String value) {
        return service.findByCanonicalURL(value);
    }

    @PostMapping(value = "/sellers")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller newSeller) {
        return service.createSeller(newSeller);
    }

    @PutMapping(value = "/sellers/id")
    public ResponseEntity<Seller> replaceSeller(@RequestBody Seller newSeller, @RequestParam String value) {
        return service.replaceSeller(newSeller, value);
    }
}
