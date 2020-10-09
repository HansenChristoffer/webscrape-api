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

import se.sogeti.webscraperapi.assemblers.SellerModelAssembler;
import se.sogeti.webscraperapi.controllers.SellerController;
import se.sogeti.webscraperapi.exceptions.SellerNotFoundException;
import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.repositories.SellerRepository;

@Service
public class SellerService {

    private final SellerRepository repository;
    private final SellerModelAssembler assembler;

    SellerService(SellerRepository repository, SellerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Seller> findById(String id) {
        Seller seller = repository.findById(id) //
                .orElseThrow(() -> new SellerNotFoundException(id));

        return assembler.toModel(seller);
    }

    public CollectionModel<EntityModel<Seller>> findAll() {
        List<EntityModel<Seller>> sellers = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(sellers, linkTo(methodOn(SellerController.class).findAll()).withSelfRel());
    }

    public CollectionModel<EntityModel<Seller>> findByName(String name) {
        List<EntityModel<Seller>> sellers = repository.findByName(name).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sellers, linkTo(methodOn(SellerController.class).findByName(name)).withSelfRel());
    }

    public CollectionModel<EntityModel<Seller>> findByHref(String href) {
        List<EntityModel<Seller>> sellers = repository.findByHref(href).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sellers, linkTo(methodOn(SellerController.class).findByHref(href)).withSelfRel());
    }

    public ResponseEntity<EntityModel<Seller>> createSeller(Seller newSeller) {
        EntityModel<Seller> entityModel = assembler.toModel(repository.save(newSeller));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Seller>> replaceSeller(Seller newSeller, String id) {
    
        Seller updatedSeller = repository.findById(id) //
          .map(seller -> {
            seller.setName(newSeller.getName());
            seller.setLocation(newSeller.getLocation());
            seller.setRegistered(newSeller.getRegistered());
            seller.setHref(newSeller.getHref());
            seller.setAdverts(newSeller.getAdverts());
            return repository.save(seller);
          }) //
          .orElseGet(() -> {
            newSeller.setId(id);
            return repository.save(newSeller);
          });
    
      EntityModel<Seller> entityModel = assembler.toModel(updatedSeller);
    
      return ResponseEntity //
          .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
          .body(entityModel);
    }
    
    public void deleteAll() {
        repository.deleteAll();
    }
}
