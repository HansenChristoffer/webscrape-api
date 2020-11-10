package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import se.sogeti.webscraperapi.models.Seller;

public interface SellerRepository extends MongoRepository<Seller, String> {

    @Query("{'_id': ?0}")
    public Optional<Seller> findByObjectId(ObjectId id);

    public Optional<Seller> findByName(String name);

    public Optional<Seller> findByHref(String href);

}
