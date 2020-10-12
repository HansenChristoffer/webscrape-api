package se.sogeti.webscraperapi.controllers;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @DeleteMapping("/links/id")
    public void deleteById(@RequestParam String value) {
        service.deleteById(value);
    }
}
