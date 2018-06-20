package com.musala.simple.students.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories()
@EnableMongoRepositories()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}