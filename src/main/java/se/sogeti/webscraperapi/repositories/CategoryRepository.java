package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

    public Optional<Category> findByName(String name);

    public Optional<Category> findByHref(String href);

}
