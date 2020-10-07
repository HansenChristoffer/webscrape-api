package se.sogeti.webscraperapi.repositories;

import org.springframework.stereotype.Repository;

import se.sogeti.webscraperapi.models.Seller;

@Repository
public interface SellerRepository extends AbstractRepository<Seller, Long> {
    
}
