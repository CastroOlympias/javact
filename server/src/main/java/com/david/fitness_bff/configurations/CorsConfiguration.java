package com.david.fitness_bff.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Instantly allow your local Angular client to communicate safely
                registry.addMapping("/graphql")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("POST", "GET", "OPTIONS");
            }
        };
    }
}