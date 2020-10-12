package se.sogeti.webscraperapi.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import se.sogeti.webscraperapi.models.Link;

public interface LinkRepository extends MongoRepository<Link, String> {
    
    @Query("{ 'isOpen' : true }")
    public Collection<Link> findOpen();

    public Optional<Link> findByHref(String href);
}
