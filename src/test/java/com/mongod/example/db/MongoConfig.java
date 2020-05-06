package com.mongod.example.db;

import com.mongodb.MongoClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient(MongoContainer mongoContainer) {
        return new MongoClient(mongoContainer.getContainerIpAddress(), mongoContainer.getFirstMappedPort());
    }

    @Bean
    public MongoContainer mongoContainer() {
        MongoContainer mongoContainer = new MongoContainer();
        mongoContainer.start();
        return mongoContainer;
    }
}
