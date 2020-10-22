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
import se.sogeti.webscraperapi.assemblers.AdvertModelAssembler;
import se.sogeti.webscraperapi.controllers.AdvertController;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.AdvertRepository;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
@Slf4j
public class AdvertService {

    private AdvertRepository repository;
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;
    private AdvertModelAssembler assembler;

    public AdvertService(AdvertRepository repository, CategoryRepository categoryRepository,
            CategoryService categoryService, AdvertModelAssembler assembler) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
        this.assembler = assembler;
    }

    public EntityModel<Advert> findById(String id) {
        Advert advert = repository.findById(id) //
                .orElseThrow(() -> new AbstractNotFoundException(id));

        return assembler.toModel(advert);
    }

    public CollectionModel<EntityModel<Advert>> findAll() {
        List<EntityModel<Advert>> adverts = repository.findAll().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(adverts, linkTo(methodOn(AdvertController.class).findAll()).withSelfRel());
    }

    public EntityModel<Advert> findByName(String name) {
        Advert advert = repository.findByName(name) //
                .orElseThrow(() -> new AbstractNotFoundException(name));

        return assembler.toModel(advert);
    }

    public EntityModel<Advert> findByHref(String href) {
        Advert advert = repository.findByHref(href) //
                .orElseThrow(() -> new AbstractNotFoundException(href));

        return assembler.toModel(advert);
    }

    public EntityModel<Advert> findByObjectNumber(String objectNumber) {
        Advert advert = repository.findByObjectNumber(objectNumber)
                .orElseThrow(() -> new AbstractNotFoundException(objectNumber));

        return assembler.toModel(advert);
    }

    public ResponseEntity<EntityModel<Advert>> createAdvert(Advert newAdvert) {
        if (!categoryRepository.findByName(newAdvert.getCategoryName()).isPresent()) {
            categoryService.createCategory(new Category(newAdvert.getCategoryName(), "N/A"));
        }

        //
        newAdvert.setAddedDate(Instant.now());
        EntityModel<Advert> entityModel = assembler.toModel(newAdvert);

        try {
            entityModel = assembler.toModel(repository.save(newAdvert));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Advert!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entityModel);
        }

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Advert>> replaceAdvert(Advert newAdvert, String id) {

        Advert updatedAdvert = repository.findById(id) //
                .map(advert -> {
                    advert.setName(newAdvert.getName());
                    advert.setCategoryName(newAdvert.getCategoryName());
                    advert.setDescription(newAdvert.getDescription());
                    advert.setHref(newAdvert.getHref());
                    advert.setObjectNumber(newAdvert.getObjectNumber());
                    advert.setPrice(newAdvert.getPrice());
                    advert.setPublished(newAdvert.getPublished());
                    advert.setSellerName(newAdvert.getSellerName());
                    advert.setImage(newAdvert.getImage());
                    return repository.save(advert);
                }) //
                .orElseGet(() -> {
                    newAdvert.setId(id);
                    return repository.save(newAdvert);
                });

        EntityModel<Advert> entityModel = assembler.toModel(updatedAdvert);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}