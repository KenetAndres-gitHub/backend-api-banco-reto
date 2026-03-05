package com.dev.backendapi.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS (Cross-Origin Resource Sharing).
 * Arquitectura de Seguridad: Lista blanca estricta de orígenes permitidos.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        // En producción, esto debería venir de una variable de entorno (.env)
                        // Por ahora, autorizamos estrictamente el puerto por defecto de Angular local
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        // allowCredentials(true) es vital si en el futuro enviamos Cookies o Tokens JWT en cabeceras HTTP-Only
                        .allowCredentials(true);
            }
        };
    }
}