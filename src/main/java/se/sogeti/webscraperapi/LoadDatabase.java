package se.sogeti.webscraperapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.models.Advert;
import se.sogeti.webscraperapi.models.Category;
import se.sogeti.webscraperapi.models.Link;
import se.sogeti.webscraperapi.models.Seller;
import se.sogeti.webscraperapi.services.AdvertService;
import se.sogeti.webscraperapi.services.CategoryService;
import se.sogeti.webscraperapi.services.LinkService;
import se.sogeti.webscraperapi.services.SellerService;

@Slf4j
@Configuration
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(CategoryService categoryService, SellerService sellerService,
      AdvertService advertService, LinkService linkService) {

    final String PRELOAD = "<$> Preloading : ";

    return args -> {
      // Clear database
      linkService.deleteAll();
      sellerService.deleteAll();
      categoryService.deleteAll();
      advertService.deleteAll();
      
      // Links
      Link mockLink = new Link("https://www.mocklink.io/coolstuff");
      log.info("{}{}", PRELOAD, linkService.createLink(mockLink));
      
      // Sellers
      Seller mockSeller = new Seller("mockSeller", "location", "registered", "sellerHref");
      sellerService.createSeller(mockSeller);
      log.info("{}{}", PRELOAD, sellerService.createSeller(mockSeller));
      
      // Categories
      Category mockCategory = new Category("mockCategory", "categoryHref");
      log.info("{}{}", PRELOAD, categoryService.createCategory(mockCategory));
      
      // Adverts
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