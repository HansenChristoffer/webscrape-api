package se.sogeti.webscraperapi.repositories;

import org.springframework.stereotype.Repository;

import se.sogeti.webscraperapi.models.Advert;

@Repository
public interface AdvertRepository extends AbstractRepository<Advert, Long> {

}
