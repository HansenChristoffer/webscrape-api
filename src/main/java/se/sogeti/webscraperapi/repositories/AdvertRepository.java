package se.sogeti.webscraperapi.repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import se.sogeti.webscraperapi.models.Advert;

public interface AdvertRepository extends MongoRepository<Advert, String> {

    public Collection<Advert> findByName(String name);

    public Collection<Advert> findByHref(String href);

}
