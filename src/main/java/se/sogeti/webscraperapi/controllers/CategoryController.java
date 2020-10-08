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

import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.services.CategoryService;

@RestController
@RequestMapping(value = "/api")
public class CategoryController {

    private CategoryService service;

    CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/category/{id}")
    public EntityModel<Category> findById(@PathVariable long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/categories")
    public CollectionModel<EntityModel<Category>> findAll() {
        return service.findAll();
    }

    @PostMapping(value = "/category")
    public ResponseEntity<EntityModel<Category>> createCategory(@RequestBody Category newCategory) {
        return service.createCategory(newCategory);
    }
}
