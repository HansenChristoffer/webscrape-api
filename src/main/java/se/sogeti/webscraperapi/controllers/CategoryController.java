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

import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.services.CategoryService;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private CategoryService service;

    CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/category/id")
    public EntityModel<Category> findById(@RequestParam String value) {
        return service.findById(value);
    }

    @GetMapping(value = "/categories")
    public CollectionModel<EntityModel<Category>> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/categories/name")
    public CollectionModel<EntityModel<Category>> findByName(@RequestParam String value) {
        return service.findByName(value);
    }

    @GetMapping(value = "/category/href")
    public CollectionModel<EntityModel<Category>> findByHref(@RequestParam String value) {
        return service.findByHref(value);
    }

    @PostMapping(value = "/category")
    public ResponseEntity<EntityModel<Category>> createCategory(@RequestBody Category newCategory) {
        return service.createCategory(newCategory);
    }

    @PutMapping(value = "/category/id")
    public ResponseEntity<EntityModel<Category>> replaceCategory(@RequestBody Category newCategory, @RequestParam String value) {
        return service.replaceCategory(newCategory, value);
    }

    @PutMapping(value = "/category/addadvert")
    public ResponseEntity<EntityModel<Category>> addAdvert(String categoryId, String advertId) {
        return service.addAdvert(categoryId, advertId);
    }
}
