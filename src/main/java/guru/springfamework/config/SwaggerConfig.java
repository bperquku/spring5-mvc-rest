package guru.springfamework.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// in case of no spring-boot app, we need to extend  WebMvcConfigurationSupport and override
// addResourceHandlers
public class SwaggerConfig{//  extends WebMvcConfigurationSupport {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/");
  }

  private ApiInfo apiInfo() {
    String title = "My REST API";
    String description = "Some custom description of API.";
    String version = "API TOS";
    String termsOfServiceUrl = "Terms of service";
    Contact contact = new Contact("John Doe", "www.example.com", "myeaddress@company.com");
    String license = "License of API";
    String licenseUrl = "API license URL";
    return new ApiInfo(
        title,
        description,
        version,
        termsOfServiceUrl,
        contact,
        license,
        licenseUrl,
        Collections.emptyList());
  }

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//      registry
//          .addResourceHandler("swagger-ui.html")
//          .addResourceLocations("classpath:/META-INF/resources");
//      registry
//          .addResourceHandler("/webjars/**")
//          .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
