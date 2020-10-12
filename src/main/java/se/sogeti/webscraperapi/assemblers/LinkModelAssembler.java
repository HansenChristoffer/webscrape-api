package se.sogeti.webscraperapi.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import se.sogeti.webscraperapi.controllers.LinkController;
import se.sogeti.webscraperapi.models.Link;

@Component
public class LinkModelAssembler implements RepresentationModelAssembler<Link, EntityModel<Link>> {

  @Override
  public EntityModel<Link> toModel(Link link) {

    return EntityModel.of(link, 
        linkTo(methodOn(LinkController.class).findOpen()).withSelfRel(),
        linkTo(methodOn(LinkController.class).findByHref(link.getHref())).withSelfRel());
  }
}