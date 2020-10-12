package se.sogeti.webscraperapi.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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

    public ResponseEntity<EntityModel<Link>> createLink(Link newLink) {
        EntityModel<Link> entityModel = assembler.toModel(repository.save(newLink));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    public ResponseEntity<EntityModel<Link>> closeLink(String href) {
        Link link = repository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
        link.setOpen(false);

        Link updatedLink = repository.save(link);

        EntityModel<Link> entityModel = assembler.toModel(updatedLink);

        return !updatedLink.isOpen()
                ? ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel)
                : null;
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
