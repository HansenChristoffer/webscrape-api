package se.sogeti.webscraperapi.controllers;

import java.util.Collection;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.services.CategoryService;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private CategoryService service;

    CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories/open")
    public Category findOpen() {
        return service.findOpen();
    }

    @GetMapping(value = "/categories/id")
    public Category findByObjectId(@RequestParam String value) {
        return service.findByObjectId(value);
    }

    @GetMapping(value = "/categories")
    public Collection<Category> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/categories/name")
    public Category findByName(@RequestParam String value) {
        return service.findByName(value);
    }

    @GetMapping(value = "/categories/href")
    public Category findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category newCategory) {
        return service.createCategory(newCategory);
    }

    @PostMapping(value = "/categories/all")
    public ResponseEntity<Collection<Category>> createCategory(@RequestBody Set<Category> newCategories) {
        return service.createAllCategories(newCategories);
    }

    @PutMapping(value = "/categories/id")
    public ResponseEntity<Category> replaceCategory(@RequestBody Category newCategory, @RequestParam String value) {
        return service.replaceCategory(newCategory, value);
    }
}
