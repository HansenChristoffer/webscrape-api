package se.sogeti.webscraperapi;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.repositories.AdvertRepository;
import se.sogeti.webscraperapi.repositories.CategoryRepository;
import se.sogeti.webscraperapi.repositories.SellerRepository;

@Slf4j
@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(CategoryRepository categoryRepository, SellerRepository sellerRepository, AdvertRepository advertRepository) {

    final String PRELOAD = "<$> Preloading : ";

    return args -> {     
      // Seller first
      Seller mockSeller = new Seller("mockSeller", "location", "registered", "sellerHref", new ArrayList<>());
      log.info("{}{}", PRELOAD, sellerRepository.saveAndFlush(mockSeller));
      // Category second
      Category mockCategory = new Category("mockCategory", "categoryHref", new ArrayList<>());
      log.info("{}{}", PRELOAD, categoryRepository.saveAndFlush(mockCategory));
      // Adverts last
      Advert mockAdvertOne = new Advert("mockAdvertOne", mockCategory, mockSeller, "description", 1000.0, "published", "objectNumber", "mockAdvertOne");
      Advert mockAdvertTwo = new Advert("mockAdvertTwo", mockCategory, mockSeller, "description", 1000.0, "published", "objectNumber", "mockAdvertTwo");
      log.info("{}{}", PRELOAD, advertRepository.saveAndFlush(mockAdvertOne));
      log.info("{}{}", PRELOAD, advertRepository.saveAndFlush(mockAdvertTwo));
    };
  }
}