package se.sogeti.webscraperapi.repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

    public Collection<Category> findByName(String name);

    public Collection<Category> findByHref(String href);

}
