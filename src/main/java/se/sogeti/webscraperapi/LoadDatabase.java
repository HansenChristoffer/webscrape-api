package se.sogeti.webscraperapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.services.AdvertService;
import se.sogeti.webscraperapi.services.CategoryService;
import se.sogeti.webscraperapi.services.SellerService;

@Slf4j
@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(CategoryService categoryService, SellerService sellerService,
      AdvertService advertService) {

    final String PRELOAD = "<$> Preloading : ";

    return args -> {
      // Delete all first
      advertService.deleteAll();
      sellerService.deleteAll();
      categoryService.deleteAll();
      // Seller first
      Seller mockSeller = new Seller("mockSeller", "location", "registered", "sellerHref");
      sellerService.createSeller(mockSeller);
      log.info("{}{}", PRELOAD, sellerService.createSeller(mockSeller));
      // Category second
      Category mockCategory = new Category("mockCategory", "categoryHref");
      log.info("{}{}", PRELOAD, categoryService.createCategory(mockCategory));
      // Adverts last
      Advert mockAdvertOne = new Advert("mockAdvertOne", mockCategory.getName(), mockSeller.getName(), "description", 1000.0, "published",
          "98348748", "mockAdvertOne");
      Advert mockAdvertTwo = new Advert("mockAdvertTwo", mockCategory.getName(), mockSeller.getName(), "description", 1000.0, "published",
          "834863423", "mockAdvertTwo");
      log.info("{}{}", PRELOAD, advertService.createAdvert(mockAdvertOne));
      log.info("{}{}", PRELOAD, advertService.createAdvert(mockAdvertTwo));
      // Update

    };
  }
}