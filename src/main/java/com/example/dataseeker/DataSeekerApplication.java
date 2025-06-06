package com.example.dataseeker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataSeekerApplication {
    public static void main(String[] args) {
        // Load environment variables from .env if present
        Dotenv.configure().ignoreIfMissing().load();
        SpringApplication.run(DataSeekerApplication.class, args);
    }
}
