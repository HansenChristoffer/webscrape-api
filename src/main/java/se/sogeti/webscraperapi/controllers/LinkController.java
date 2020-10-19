package se.sogeti.webscraperapi.controllers;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.sogeti.webscraperapi.models.Link;
import se.sogeti.webscraperapi.services.LinkService;

@RestController
@RequestMapping(value = "/api")
public class LinkController {

    private LinkService service;

    public LinkController(LinkService service) {
        this.service = service;
    }

    @GetMapping("/links/open")
    public EntityModel<Link> findOpen() {
        return service.findOpen();
    }

    @GetMapping("/links/href")
    public EntityModel<Link> findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @PostMapping("/links")
    public ResponseEntity<EntityModel<Link>> createLink(@RequestBody Link link) {
        return service.createLink(link);
    }

    @PostMapping("/links/all")
    public ResponseEntity<CollectionModel<EntityModel<Link>>> createAllLinks(@RequestBody List<Link> links) {
        return service.createAllLinks(links);
    }

    @DeleteMapping("/links/id")
    public ResponseEntity<Link> deleteById(@RequestParam String value) {
        return service.deleteById(value) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}