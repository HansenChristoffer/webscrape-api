package se.sogeti.webscraperapi.services;

import java.time.Instant;
import java.util.Collection;

import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByObjectId(String id) {
        return categoryRepository.findByObjectId(new ObjectId(id)).orElseThrow(() -> new AbstractNotFoundException(id));
    }

    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new AbstractNotFoundException(name));
    }

    public Category findByHref(String href) {
        return categoryRepository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
    }

    public ResponseEntity<Category> createCategory(Category newCategory) {
        newCategory.setAddedDate(Instant.now());

        try {
            return ResponseEntity.ok(categoryRepository.save(newCategory));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Category!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Category());
    }

    public ResponseEntity<Category> replaceCategory(Category newCategory, String id) {
        return categoryRepository.findByObjectId(new ObjectId(id)).map(category -> {
            category.setName(newCategory.getName());
            category.setHref(newCategory.getHref());
            return ResponseEntity.ok(categoryRepository.save(category));
        }).orElseGet(() -> {
            newCategory.setId(id);
            return ResponseEntity.ok(categoryRepository.save(newCategory));
        });
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}