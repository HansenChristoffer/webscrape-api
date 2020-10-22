package se.sogeti.webscraperapi.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.assemblers.LinkModelAssembler;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.exceptions.EmptyLinkListException;
import se.sogeti.webscraperapi.models.Link;
import se.sogeti.webscraperapi.repositories.LinkRepository;

@Service
@Slf4j
public class LinkService {

    private LinkRepository repository;
    private LinkModelAssembler assembler;
    private static final Random RAND = new Random();

    public LinkService(LinkRepository repository, LinkModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public EntityModel<Link> findOpen() {
        List<EntityModel<Link>> links = repository.findOpen().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        if (links.isEmpty()) {
            throw new EmptyLinkListException();
        }

        EntityModel<Link> link = assembler.toModel(links.get(RAND.nextInt(links.size())).getContent());
        closeLink(link.getContent().getHref());

        return link;
    }

    public EntityModel<Link> findByHref(String href) {
        Link link = repository.findByHref(href) //
                .orElseThrow(() -> new AbstractNotFoundException(href));

        return assembler.toModel(link);
    }

    public ResponseEntity<CollectionModel<EntityModel<Link>>> createAllLinks(List<Link> newLinks) {
        newLinks.forEach(l -> l.setIsOpen(true));

        return ResponseEntity.ok(assembler.toCollectionModel(repository.saveAll(newLinks)));
    }

    public ResponseEntity<EntityModel<Link>> createLink(Link newLink) {
        newLink.setIsOpen(true);
        EntityModel<Link> entityModel = assembler.toModel(newLink);

        try {
            entityModel = assembler.toModel(repository.save(newLink));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Link!");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(entityModel);
        }

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Link>> closeLink(String href) {
        Link link = repository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
        link.setIsOpen(false);

        Link updatedLink = repository.save(link);

        EntityModel<Link> entityModel = assembler.toModel(updatedLink);

        return !updatedLink.isOpen()
                ? ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel)
                : null;
    }

    public boolean deleteById(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            if (!repository.existsById(id)) {
                return true;
            }
        } else {
            throw new AbstractNotFoundException(id);
        }

        return false;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
