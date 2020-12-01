package se.sogeti.webscraperapi;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
                .paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/.*"), regex("/api/.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Webscrape API").description("Webscrape API reference for developers")
                .termsOfServiceUrl("https://www.sogeti.se/anvandarvillkor/").license("Secret License")
                .licenseUrl("https://www.sogeti.se/anvandarvillkor/").version("2.0").build();
    }

}