package com.dev.backendapi.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Bancaria MVP")
                        .version("1.0.0")
                        .description("Documentación de la API REST para el sistema de gestión de cuentas y movimientos bancarios.")
                        .contact(new Contact()
                                .name("Kenet Chungandro")
                                .email("kenbok2018@gmail.com")));
    }
}