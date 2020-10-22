package se.sogeti.webscraperapi.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.assemblers.CategoryModelAssembler;
import se.sogeti.webscraperapi.controllers.CategoryController;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;

    CategoryService(CategoryRepository repository, CategoryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Category> findById(String id) {
        Category category = repository.findById(id) //
                .orElseThrow(() -> new AbstractNotFoundException(id));

        return assembler.toModel(category);
    }

    public CollectionModel<EntityModel<Category>> findAll() {
        List<EntityModel<Category>> categories = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).findAll()).withSelfRel());
    }

    public EntityModel<Category> findByName(String name) {
        Category category = repository.findByName(name) //
                .orElseThrow(() -> new AbstractNotFoundException(name));

                return assembler.toModel(category);
    }

    public EntityModel<Category> findByHref(String href) {
        Category category = repository.findByHref(href) //
                .orElseThrow(() -> new AbstractNotFoundException(href));

                return assembler.toModel(category);
    }

    public ResponseEntity<EntityModel<Category>> createCategory(Category newCategory) {
        newCategory.setAddedDate(Instant.now());

        EntityModel<Category> entityModel = assembler.toModel(newCategory);

        try {
        entityModel = assembler.toModel(repository.save(newCategory));
    } catch (DuplicateKeyException e) {
        log.info("Duplicate key at Category!");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(entityModel);
    }

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Category>> replaceCategory(Category newCategory, String id) {
    
        Category updatedCategory = repository.findById(id) //
          .map(category -> {
            category.setName(newCategory.getName());
            category.setHref(newCategory.getHref());
            return repository.save(category);
          }) //
          .orElseGet(() -> {
            newCategory.setId(id);
            return repository.save(newCategory);
          });
    
      EntityModel<Category> entityModel = assembler.toModel(updatedCategory);
    
      return ResponseEntity //
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
          .body(entityModel);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}