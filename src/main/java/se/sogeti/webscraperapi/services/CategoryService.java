package se.sogeti.webscraperapi.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.repositories.CategoryRepository;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SettingsService settingsService;
    private static final Random RAND = new Random();

    CategoryService(CategoryRepository categoryRepository, SettingsService settingsService) {
        this.categoryRepository = categoryRepository;
        this.settingsService = settingsService;
    }

    public ResponseEntity<Category> findOpen() {
        List<Category> categories = new ArrayList<>(categoryRepository.findOpen());

        if (categories.isEmpty()) {
            settingsService.setActive("ls", false);
            log.info("No open categories - Toggling the active status of Link scraper!");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Category());
        }

        Category category = categories.get(RAND.nextInt(categories.size()));
        closeCategory(category.getHref());

        return ResponseEntity.ok(category);

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
        categoryRepository.deleteAll();
        Set<Category> savedCategory = new HashSet<>();
        Category newCategory;

        for (Category c : newCategories) {
            c.setOpen(true);
            try {

                newCategory = categoryRepository.save(c);

                if (!newCategory.getHref().isBlank()) {
                    savedCategory.add(newCategory);
                }

            } catch (Exception e) {
                log.error("CreateAllCategory().Exception == {}", e.getMessage());
            }
        }

        if (savedCategory.size() == newCategories.size()) {
            return ResponseEntity.ok(savedCategory);
        } else if (!savedCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(savedCategory);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(savedCategory);
        }
    }

    // public ResponseEntity<Collection<Category>> createAllCategories(Set<Category>
    // newCategories) {
    // try {
    // return ResponseEntity.ok(categoryRepository.saveAll(newCategories));
    // } catch (DuplicateKeyException e) {
    // log.info("Duplicate key at Category!");
    // }

    // return ResponseEntity.status(HttpStatus.CONFLICT).body(new ArrayList<>());
    // }

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

}