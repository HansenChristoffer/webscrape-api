package se.sogeti.webscraperapi.repositories;

import org.springframework.stereotype.Repository;

import se.sogeti.webscraperapi.models.Category;

@Repository
public interface CategoryRepository extends AbstractRepository<Category, Long> {

}
