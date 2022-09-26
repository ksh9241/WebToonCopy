package project.toy.webtoon_copy.config;

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

    /**
     * @Value
     *  - apis : 대상 패키지 설정<br>
     *  - paths : 어떤 식으로 시작하는 api를 보여줄 것인지?<br>
     *  - any : wjsqn<br>
     *
     * @URL : http://localhost:8080/swagger-ui.html
     * */
    @Bean
    public Docket restAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("project.toy"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("WEBTOON Copy API")
                .version("2.9.0")
                .description("토이프로젝트 Webtoon Copy Swagger Api")
                .build();
    }
}
