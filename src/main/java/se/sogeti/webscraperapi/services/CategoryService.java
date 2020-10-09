package se.sogeti.webscraperapi.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import se.sogeti.webscraperapi.assemblers.CategoryModelAssembler;
import se.sogeti.webscraperapi.controllers.CategoryController;
import se.sogeti.webscraperapi.exceptions.AdvertNotFoundException;
import se.sogeti.webscraperapi.exceptions.CategoryNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.AdvertRepository;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final AdvertRepository adRepo;
    private final CategoryModelAssembler assembler;

    CategoryService(CategoryRepository repository, AdvertRepository adRepo, CategoryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
        this.adRepo = adRepo;
    }

    public EntityModel<Category> findById(String id) {
        Category category = repository.findById(id) //
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return assembler.toModel(category);
    }

    public CollectionModel<EntityModel<Category>> findAll() {
        List<EntityModel<Category>> categories = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).findAll()).withSelfRel());
    }

    public CollectionModel<EntityModel<Category>> findByName(String name) {
        List<EntityModel<Category>> categories = repository.findByName(name).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).findByName(name)).withSelfRel());
    }

    public CollectionModel<EntityModel<Category>> findByHref(String href) {
        List<EntityModel<Category>> categories = repository.findByHref(href).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).findByHref(href)).withSelfRel());
    }

    public ResponseEntity<EntityModel<Category>> createCategory(Category newCategory) {
        EntityModel<Category> entityModel = assembler.toModel(repository.save(newCategory));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Category>> replaceCategory(Category newCategory, String id) {
    
        Category updatedCategory = repository.findById(id) //
          .map(category -> {
            category.setName(newCategory.getName());
            category.setHref(newCategory.getHref());
            category.setAdverts(newCategory.getAdverts());
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

    public ResponseEntity<EntityModel<Category>> addAdvert(String categoryId, String advertId) {
        Category category = repository.findById(categoryId) //
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        Advert advert = adRepo.findById(advertId) //
                .orElseThrow(() -> new AdvertNotFoundException(advertId));

        category.getAdverts().add(advert.getId());
        repository.save(category);
        
        EntityModel<Category> entityModel = assembler.toModel(category);
    
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
