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

import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.services.AdvertService;

@RestController
@RequestMapping(value = "/api")
public class AdvertController {

    private AdvertService service;

    AdvertController(AdvertService service) {
        this.service = service;
    }

    @GetMapping(value = "/adverts/id")
    public Advert findByObjectId(@RequestParam String value) {
        return service.findByObjectId(value);
    }

    @GetMapping(value = "/adverts")
    public Collection<Advert> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/adverts/name")
    public Advert findByName(@RequestParam String value) {
        return service.findByName(value);
    }

    @GetMapping(value = "/adverts/href")
    public Advert findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @GetMapping(value = "/adverts/obn")
    public Advert findByObjectNumber(@RequestParam String value) {
        return service.findByObjectNumber(value);
    }

    @PostMapping(value = "/adverts")
    public ResponseEntity<Advert> createAdvert(@RequestBody Advert newAdvert) {
        return service.createAdvert(newAdvert);
    }

    @PutMapping(value = "/adverts/id")
    public ResponseEntity<Advert> replaceAdvert(@RequestBody Advert newAdvert, @RequestParam String value) {
        return service.replaceAdvert(newAdvert, value);
    }

}
