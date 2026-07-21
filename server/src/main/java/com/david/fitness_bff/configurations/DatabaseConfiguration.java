package com.david.fitness_bff.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        // 1. Detect Worker/CPU Core Count (equivalent to os.cpus().length)
        int workerCount = Runtime.getRuntime().availableProcessors();

        // 2. Set Target Capacity (Your "480 Strategy")
        int globalConnectionTarget = 480;

        // 3. Calculate Pool Size Per Worker
        int dynamicPoolSize = Math.max(1, globalConnectionTarget / workerCount);

        System.out.println("⚡ Database: Optimizing for " + workerCount + " CPU cores.");
        System.out.println("⚡ Database: Connection Pool size set to " + dynamicPoolSize);

        // 4. Configure HikariCP DataSource
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/trinity");
        config.setUsername("postgres"); // 1. Change from "your_user" to "postgres"
        config.setPassword("123456789"); // 2. Put the password you just set during installation

        // Apply your dynamic strategy!
        config.setMaximumPoolSize(dynamicPoolSize);
        config.setMinimumIdle(Math.max(1, dynamicPoolSize / 2));

        return new HikariDataSource(config);
    }
}