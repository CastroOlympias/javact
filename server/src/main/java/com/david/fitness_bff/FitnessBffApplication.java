package com.david.fitness_bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(FitnessBffApplication.class, args);
    }
}

// ./mvnw clean spring-boot:run
// ./mvnw spring-boot:run
// ./server/mvnw -f server/pom.xml clean spring-boot:run