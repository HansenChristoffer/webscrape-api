package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Seller;

public interface SellerRepository extends MongoRepository<Seller, String> {
    
    public Optional<Seller> findByName(String name);

    public Optional<Seller> findByHref(String href);

}
