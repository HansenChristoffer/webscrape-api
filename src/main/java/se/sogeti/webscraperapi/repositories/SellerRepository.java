package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import se.sogeti.webscraperapi.models.Seller;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {

    @Query("{'_id': ?0}")
    public Optional<Seller> findByObjectId(ObjectId id);

    public Optional<Seller> findByAlias(String alias);

    public Optional<Seller> findByMemberId(Integer memberId);

    public Optional<Seller> findByCanonicalURL(String canonicalURL);

}
