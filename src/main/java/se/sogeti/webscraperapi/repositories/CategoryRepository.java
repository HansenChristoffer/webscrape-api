package se.sogeti.webscraperapi.repositories;

import java.util.Collection;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import se.sogeti.webscraperapi.models.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query("{'_id': ?0}")
    public Optional<Category> findByObjectId(ObjectId id);

    @Query("{ 'isOpen' : true }")
    public Collection<Category> findOpen();

    public Optional<Category> findByName(String name);

    public Optional<Category> findByHref(String href);

}
