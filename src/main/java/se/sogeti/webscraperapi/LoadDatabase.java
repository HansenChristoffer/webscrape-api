package se.sogeti.webscraperapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import se.sogeti.webscraperapi.services.AdvertService;
import se.sogeti.webscraperapi.services.CategoryService;
import se.sogeti.webscraperapi.services.LinkService;
import se.sogeti.webscraperapi.services.SellerService;

@Slf4j
@Configuration
public class LoadDatabase {

  private CategoryService categoryService;
  private SellerService sellerService;
  private AdvertService advertService;
  private LinkService linkService;

  @Bean
  CommandLineRunner initDatabase(CategoryService categoryService, SellerService sellerService,
      AdvertService advertService, LinkService linkService) {

    this.categoryService = categoryService;
    this.sellerService = sellerService;
    this.advertService = advertService;
    this.linkService = linkService;

    return args -> {
      // Clear database
      linkService.deleteAll();
      sellerService.deleteAll();
      categoryService.deleteAll();
      advertService.deleteAll();
    };
  }

}