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
import se.sogeti.webscraperapi.exceptions.AdvertNotFoundException;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.repositories.AdvertRepository;

@Service
public class AdvertService {

    private final AdvertRepository repository;
    private final AdvertModelAssembler assembler;

    AdvertService(AdvertRepository repository, AdvertModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Advert> findById(long id) {
        Advert advert = repository.findById(id) //
                .orElseThrow(() -> new AdvertNotFoundException(id));

        return assembler.toModel(advert);
    }

    public CollectionModel<EntityModel<Advert>> findAll() {
        List<EntityModel<Advert>> adverts = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(adverts, linkTo(methodOn(AdvertController.class).findAll()).withSelfRel());
    }

    public ResponseEntity<EntityModel<Advert>> createAdvert(Advert newAdvert) {
        EntityModel<Advert> entityModel = assembler.toModel(repository.save(newAdvert));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
}