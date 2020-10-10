package se.sogeti.webscraperapi.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Advert;

public interface AdvertRepository extends MongoRepository<Advert, String> {

    public Optional<Advert> findByName(String name);

    public Optional<Advert> findByHref(String href);

}
