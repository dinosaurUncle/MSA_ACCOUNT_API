package me.dinosauruncle.service.portal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket wholeApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/**"))
                .build();
    }

    @Bean
    public Docket accountApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("account")
                .apiInfo(metadata())
                .select()
                .paths(PathSelectors.ant("/account/**"))
                .build();
    }

    @Bean
    public Docket eventMessageApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("eventMessage")
                .apiInfo(metadata())
                .select()
                .paths(PathSelectors.ant("/eventMessage/**"))
                .build();
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("dinosaur uncle springboot API")
                .description("This api offer infomation for account, page, role, event-message")
                .version("1.0.0")
                .build();
    }
}
