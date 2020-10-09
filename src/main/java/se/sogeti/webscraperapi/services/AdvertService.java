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

    private AdvertRepository repository;
    private AdvertModelAssembler assembler;

    public AdvertService(AdvertRepository repository, AdvertModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Advert> findById(String id) {
        Advert advert = repository.findById(id) //
                .orElseThrow(() -> new AdvertNotFoundException(id));

        return assembler.toModel(advert);
    }

    public CollectionModel<EntityModel<Advert>> findAll() {
        List<EntityModel<Advert>> adverts = repository.findAll().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(adverts, linkTo(methodOn(AdvertController.class).findAll()).withSelfRel());
    }

    public CollectionModel<EntityModel<Advert>> findByName(String name) {
        List<EntityModel<Advert>> adverts = repository.findByName(name).stream().map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(adverts, linkTo(methodOn(AdvertController.class).findByName(name)).withSelfRel());
    }

    public CollectionModel<EntityModel<Advert>> findByHref(String href) {
        List<EntityModel<Advert>> adverts = repository.findByHref(href).stream().map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(adverts, linkTo(methodOn(AdvertController.class).findByHref(href)).withSelfRel());
    }

    public ResponseEntity<EntityModel<Advert>> createAdvert(Advert newAdvert) {
        // TODO Check if category exists or not, if not then throw exception otherwise
        // fetch and add to newAdvert
        // TODO Check if seller !exists then create the user from the info given if
        // possible(Throw exception if not) otherwise fetch and add to newAdvert
        EntityModel<Advert> entityModel = assembler.toModel(repository.save(newAdvert));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Advert>> replaceAdvert(Advert newAdvert, String id) {
    
        Advert updatedAdvert = repository.findById(id) //
          .map(advert -> {
            advert.setName(newAdvert.getName());
            advert.setCategory(newAdvert.getCategory());
            advert.setDescription(newAdvert.getDescription());
            advert.setHref(newAdvert.getHref());
            advert.setObjectNumber(newAdvert.getObjectNumber());
            advert.setPrice(newAdvert.getPrice());
            advert.setPublished(newAdvert.getPublished());
            advert.setSeller(newAdvert.getSeller());
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