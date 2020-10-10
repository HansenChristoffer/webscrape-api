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

import se.sogeti.webscraperapi.assemblers.AdvertModelAssembler;
import se.sogeti.webscraperapi.controllers.AdvertController;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.repositories.AdvertRepository;
import se.sogeti.webscraperapi.repositories.CategoryRepository;
import se.sogeti.webscraperapi.repositories.SellerRepository;

@Service
public class AdvertService {

    private AdvertRepository repository;
    private CategoryRepository categoryRepository;
    private SellerRepository sellerRepository;
    private AdvertModelAssembler assembler;

    public AdvertService(AdvertRepository repository, CategoryRepository categoryRepository,
            SellerRepository sellerRepository, AdvertModelAssembler assembler) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.sellerRepository = sellerRepository;
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
        categoryRepository.findByName(newAdvert.getCategoryName())
                .orElseThrow(() -> new AbstractNotFoundException(newAdvert.getCategoryName()));
                
        sellerRepository.findByName(newAdvert.getSellerName())
                .orElseThrow(() -> new AbstractNotFoundException(newAdvert.getSellerName()));

        EntityModel<Advert> entityModel = assembler.toModel(repository.save(newAdvert));

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