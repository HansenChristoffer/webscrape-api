package se.sogeti.webscraperapi.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.exceptions.AbstractNotFoundException;
import se.sogeti.webscraperapi.exceptions.EmptyListException;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private static final Random RAND = new Random();

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findOpen() {
        List<Category> links = new ArrayList<>(categoryRepository.findOpen());

        if (links.isEmpty()) {
            throw new EmptyListException();
        }

        Category category = links.get(RAND.nextInt(links.size()));
        closeCategory(category.getHref());

        return category;
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

    public ResponseEntity<Collection<Category>> createAllCategories(Set<Category> newCategories) {
        try {
            return ResponseEntity.ok(categoryRepository.saveAll(newCategories));
        } catch (DuplicateKeyException e) {
            log.info("Duplicate key at Category!");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ArrayList<>());
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

    public ResponseEntity<Category> closeCategory(String href) {
        Category category = categoryRepository.findByHref(href).orElseThrow(() -> new AbstractNotFoundException(href));
        category.setOpen(false);

        Category updatedCategory = categoryRepository.save(category);

        return !updatedCategory.isOpen() ? ResponseEntity.ok(category)
                : ResponseEntity.status(HttpStatus.CONFLICT).body(new Category());
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}