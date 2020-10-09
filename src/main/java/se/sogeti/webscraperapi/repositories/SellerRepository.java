package se.sogeti.webscraperapi.repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Seller;

public interface SellerRepository extends MongoRepository<Seller, String> {
    
    public Collection<Seller> findByName(String name);

    public Collection<Seller> findByHref(String href);

}
