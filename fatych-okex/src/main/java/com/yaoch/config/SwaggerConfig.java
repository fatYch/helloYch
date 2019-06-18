package com.yaoch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("肥美的韭菜场")
                        .description("本文档由盐焗肥肠特约赞助!")
                        .version("1.0.0")
                        .contact(new Contact("盐焗肥肠","http://blog,yaoch.top","yaocanhong@timesgroup.cn"))
                        .license("欢迎性感程序员在线调教。")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yaoch"))
                .paths(PathSelectors.any())
                .build();
    }

}
