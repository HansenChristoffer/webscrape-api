package se.sogeti.webscraperapi.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import se.sogeti.webscraperapi.controllers.CategoryController;
import se.sogeti.webscraperapi.models.Category;

@Component
public class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {

  @Override
  public EntityModel<Category> toModel(Category category) {

    return EntityModel.of(category, 
        linkTo(methodOn(CategoryController.class).findById(category.getId())).withSelfRel(),
        linkTo(methodOn(CategoryController.class).findAll()).withRel("categories"));
  }
}