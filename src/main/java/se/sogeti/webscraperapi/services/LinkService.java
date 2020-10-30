package se.sogeti.webscraperapi.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.exceptions.EmptyListException;
import se.sogeti.webscraperapi.models.Link;
import se.sogeti.webscraperapi.repositories.LinkRepository;

@Service
@Slf4j
public class LinkService {

    private LinkRepository linkRepository;
    private static final Random RAND = new Random();

    public LinkService(LinkRepository repository) {
        this.linkRepository = repository;
    }

    public Link findOpen() {
        List<Link> links = new ArrayList<>(linkRepository.findOpen());

        if (links.isEmpty()) {
            throw new EmptyListException();
        }

        Link link = links.get(RAND.nextInt(links.size()));
        closeLink(link.getHref());

        return link;
    }

    public Collection<Link> findAll() {
        return linkRepository.findAll();
    }

    public Link findByHref(String href) {
        return linkRepository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
    }

    public ResponseEntity<Collection<Link>> createAllLinks(Set<Link> newLinks) {
        // boolean ok = true;

        // for (Link link : newLinks) {
        // link.setIsOpen(true);

        // try {
        // linkRepository.save(link);
        // ok = true;
        // } catch (DuplicateKeyException dke) {
        // log.error("Duplicate key at link! - [{}]", link);
        // ok = true;
        // } catch (Exception e) {
        // log.error("Major error when saving link - [{}]", link);
        // log.error("{}", e.getMessage());
        // ok = false;
        // }
        // }

        // return ok ? ResponseEntity.ok(newLinks) :
        // ResponseEntity.status(HttpStatus.CONFLICT).body(new HashSet<>());
        newLinks.forEach(l -> l.setIsOpen(true));

        try {
            return ResponseEntity.ok(linkRepository.saveAll(newLinks));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Link!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ArrayList<>());
    }

    public ResponseEntity<Link> createLink(Link newLink) {
        newLink.setIsOpen(true);

        try {
            return ResponseEntity.ok(linkRepository.save(newLink));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Link!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Link());
    }

    public ResponseEntity<Link> closeLink(String href) {
        Link link = linkRepository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
        link.setIsOpen(false);

        Link updatedLink = linkRepository.save(link);

        return !updatedLink.isOpen() ? ResponseEntity.ok(link)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(new Link());
    }

    public ResponseEntity<String> deleteById(String id) {
        if (linkRepository.existsById(id)) {
            linkRepository.deleteById(id);
            if (!linkRepository.existsById(id)) {
                return ResponseEntity.ok("Successfully deleted the Link!");
            }
        } else {
            throw new AbstractNotFoundException(id);
        }

        return ResponseEntity.badRequest().body("Something iffy from your side!");
    }

    public void deleteAll() {
        linkRepository.deleteAll();
    }
}
