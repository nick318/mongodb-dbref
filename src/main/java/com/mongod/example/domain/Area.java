package com.mongod.example.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "areas")
@Data
@Accessors(chain = true)
public class Area {
    @Id
    private String id;
    private String name;
}
