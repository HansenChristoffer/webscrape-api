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

  private final String preloadLoggerMsg = "<$> Preloading : ";
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

      // Links
      //mockLinks();

      // Adverts
      //mockAdverts();
    };
  }

  private void mockLinks() {
    Link mockLink = new Link("https://www.mocklink.io/coolstuff");
    Link mockLink1 = new Link("https://www.mocklink.io/pewpew");
    Link mockLink2 = new Link("https://www.mocklink.io/cowcow");
    Link mockLink3 = new Link("https://www.mocklink.io/bajs");
    log.info("{}{}", preloadLoggerMsg, linkService.createLink(mockLink));
    log.info("{}{}", preloadLoggerMsg, linkService.createLink(mockLink1));
    log.info("{}{}", preloadLoggerMsg, linkService.createLink(mockLink2));
    log.info("{}{}", preloadLoggerMsg, linkService.createLink(mockLink3));
  }

  private String mockSellers() {
    Seller mockSeller = new Seller("mockSeller", "location", "registered", "sellerHref");
    sellerService.createSeller(mockSeller);
    log.info("{}{}", preloadLoggerMsg, sellerService.createSeller(mockSeller));

    return mockSeller.getName();
  }

  private String mockCategories() {
    Category mockCategory = new Category("mockCategory", "categoryHref");
    log.info("{}{}", preloadLoggerMsg, categoryService.createCategory(mockCategory));

    return mockCategory.getName();
  }

  private void mockAdverts() {
    Advert mockAdvertOne = new Advert("mockAdvertOne", mockCategories(), mockSellers(), "description",
        1000.0, "published", "98348748", "mockAdvertOne", "mockCondition", "mockBrand", "mockSize", "mockColor",
        new byte[0]);
    Advert mockAdvertTwo = new Advert("mockAdvertTwo", mockCategories(), mockSellers(), "description",
        1000.0, "published", "834863423", "mockAdvertTwo", "mockCondition", "mockBrand", "mockSize", "mockColor",
        new byte[0]);
    log.info("{}{}", preloadLoggerMsg, advertService.createAdvert(mockAdvertOne));
    log.info("{}{}", preloadLoggerMsg, advertService.createAdvert(mockAdvertTwo));
  }
}