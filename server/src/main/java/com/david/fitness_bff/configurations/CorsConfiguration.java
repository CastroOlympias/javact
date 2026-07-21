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
                registry.addMapping("/graphql")
                        // 💡 Updated from 4200 -> 5173 for Vite + React
                        .allowedOrigins("http://localhost:5173", "http://localhost:4200")
                        .allowedMethods("POST", "GET", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}