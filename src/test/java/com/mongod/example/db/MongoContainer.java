package com.mongod.example.db;

import org.testcontainers.containers.GenericContainer;


public class MongoContainer extends GenericContainer<MongoContainer> {
    public MongoContainer() {
        super("mongo:4.2");
        withExposedPorts(27017);
    }
}
