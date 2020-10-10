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

  private static final String P = "sellers";

  @Override
  public EntityModel<Seller> toModel(Seller seller) {

    return EntityModel.of(seller, 
        linkTo(methodOn(SellerController.class).findById(seller.getId())).withSelfRel(),
        linkTo(methodOn(SellerController.class).findAll()).withRel(P),
        linkTo(methodOn(SellerController.class).findByName(seller.getName())).withSelfRel(),
        linkTo(methodOn(SellerController.class).findByHref(seller.getHref())).withSelfRel(),
        linkTo(methodOn(SellerController.class).createSeller(seller)).withSelfRel(),
        linkTo(methodOn(SellerController.class).replaceSeller(new Seller(), seller.getId())).withSelfRel());
  }
}