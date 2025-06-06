package com.example.dataseeker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataSeekerApplication {
    public static void main(String[] args) {
        // Load environment variables from .env if present and make them
        // available as system properties so Spring can inject them
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
        SpringApplication.run(DataSeekerApplication.class, args);
    }
}
