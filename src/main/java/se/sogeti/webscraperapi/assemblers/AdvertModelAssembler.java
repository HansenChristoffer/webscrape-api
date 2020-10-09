package se.sogeti.webscraperapi.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import se.sogeti.webscraperapi.controllers.AdvertController;
import se.sogeti.webscraperapi.models.Advert;

@Component
public class AdvertModelAssembler implements RepresentationModelAssembler<Advert, EntityModel<Advert>> {

  private static final String P = "adverts";

  @Override
  public EntityModel<Advert> toModel(Advert advert) {

    return EntityModel.of(advert, 
        linkTo(methodOn(AdvertController.class).findById(advert.getId())).withSelfRel(),
        linkTo(methodOn(AdvertController.class).findAll()).withRel(P),
        linkTo(methodOn(AdvertController.class).findByName(advert.getName())).withRel(P),
        linkTo(methodOn(AdvertController.class).findByHref(advert.getHref())).withRel(P));
  }
}