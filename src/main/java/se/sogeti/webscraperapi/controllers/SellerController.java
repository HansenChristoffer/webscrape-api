package se.sogeti.webscraperapi.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "/seller/{id}")
    public EntityModel<Seller> findById(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/sellers")
    public CollectionModel<EntityModel<Seller>> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "/seller")
    public ResponseEntity<EntityModel<Seller>> createSeller(@RequestBody Seller newSeller) {
        return service.createSeller(newSeller);
    }
}
