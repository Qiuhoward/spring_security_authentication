package com.example.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <swagger_api_document>
 * please shutdown security_authentication before look swagger_api_document
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerAPI(){
        return new OpenAPI()
                .info(new Info()
                .version("0.0.1")
                .description("springboot_api_document")
                .title("springboot_demo_API"))
                ;
    }

}
