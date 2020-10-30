package se.sogeti.webscraperapi.controllers;

import java.util.Collection;
import java.util.Set;

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
    public Link findOpen() {
        return service.findOpen();
    }

    @GetMapping("/links/href")
    public Link findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @GetMapping("/links")
    public Collection<Link> findAll() {
        return service.findAll();
    }

    @PostMapping("/links")
    public ResponseEntity<Link> createLink(@RequestBody Link link) {
        return service.createLink(link);
    }

    @PostMapping("/links/all")
    public ResponseEntity<Collection<Link>> createAllLinks(@RequestBody Set<Link> links) {
        return service.createAllLinks(links);
    }

    @DeleteMapping("/links/id")
    public ResponseEntity<String> deleteById(@RequestParam String value) {
        return service.deleteById(value);
    }
}