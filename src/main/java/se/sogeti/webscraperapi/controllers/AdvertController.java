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

import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.services.AdvertService;

@RestController
@RequestMapping(value = "/api")
public class AdvertController {

    private AdvertService service;

    AdvertController(AdvertService service) {
        this.service = service;
    }

    @GetMapping(value = "/advert/id")
    public EntityModel<Advert> findById(@RequestParam String value) {
        return service.findById(value);
    }

    @GetMapping(value = "/adverts")
    public CollectionModel<EntityModel<Advert>> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/adverts/name")
    public CollectionModel<EntityModel<Advert>> findByName(@RequestParam String value) {
        return service.findByName(value);
    }

    @GetMapping(value = "/adverts/href")
    public CollectionModel<EntityModel<Advert>> findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @PostMapping(value = "/advert")
    public ResponseEntity<EntityModel<Advert>> createAdvert(@RequestBody Advert newAdvert) {
        return service.createAdvert(newAdvert);
    }

    @PutMapping(value = "/advert/id")
    public ResponseEntity<EntityModel<Advert>> replaceAdvert(@RequestBody Advert newAdvert, @RequestParam String value) {
        return service.replaceAdvert(newAdvert, value);
    }

}
