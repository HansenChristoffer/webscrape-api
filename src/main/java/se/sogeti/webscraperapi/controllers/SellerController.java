package se.sogeti.webscraperapi.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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

    @GetMapping(value = "/seller/id")
    public EntityModel<Seller> findById(@RequestParam String value) {
        return service.findById(value);
    }

    @GetMapping(value = "/sellers")
    public CollectionModel<EntityModel<Seller>> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/sellers/name")
    public CollectionModel<EntityModel<Seller>> findByName(@RequestParam String value) {
        return service.findByName(value);
    }

    @GetMapping(value = "/sellers/href")
    public CollectionModel<EntityModel<Seller>> findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @PostMapping(value = "/seller")
    public ResponseEntity<EntityModel<Seller>> createSeller(@RequestBody Seller newSeller) {
        return service.createSeller(newSeller);
    }

    @PutMapping(value = "/seller/id")
    public ResponseEntity<EntityModel<Seller>> replaceSeller(@RequestBody Seller newSeller, @RequestParam String value) {
        return service.replaceSeller(newSeller, value);
    }
}
