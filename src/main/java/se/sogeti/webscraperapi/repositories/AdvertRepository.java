package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import se.sogeti.webscraperapi.models.Advert;

public interface AdvertRepository extends MongoRepository<Advert, String> {

    @Query("{'_id': ?0}")
    public Optional<Advert> findByObjectId(ObjectId id);

    public Optional<Advert> findByName(String name);

    public Optional<Advert> findByHref(String href);

    public Optional<Advert> findByObjectNumber(String objectNumber);

}
