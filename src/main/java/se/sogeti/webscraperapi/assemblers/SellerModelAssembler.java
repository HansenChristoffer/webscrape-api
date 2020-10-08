package se.sogeti.webscraperapi.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import se.sogeti.webscraperapi.controllers.SellerController;
import se.sogeti.webscraperapi.models.Seller;

@Component
public class SellerModelAssembler implements RepresentationModelAssembler<Seller, EntityModel<Seller>> {

  @Override
  public EntityModel<Seller> toModel(Seller seller) {

    return EntityModel.of(seller, 
        linkTo(methodOn(SellerController.class).findById(seller.getId())).withSelfRel(),
        linkTo(methodOn(SellerController.class).findAll()).withRel("sellers"));
  }
}