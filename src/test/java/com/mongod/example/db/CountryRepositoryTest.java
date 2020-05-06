package com.mongod.example.db;

import com.mongod.example.domain.Area;
import com.mongod.example.domain.Country;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.testcontainers.junit.jupiter.Container;

@MongoDbTest
class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Container
    @Autowired
    protected MongoContainer mongo;

    @Test
    void shouldSaveCountry() {
        Country country = new Country().setName("test-country")
                .setArea(new Area().setName("test-area"));

        countryRepository.save(country);
        Assertions.assertThat(countryRepository.findAll()).hasSize(1);
        Assertions.assertThat(mongoOperations.findAll(Area.class)).hasSize(1);
        Assertions.assertThat(countryRepository.findAll().get(0).getArea().getName()).isEqualTo("test-area");
    }

    @Test
    void shouldSaveCountryWithExistedArea() {
        Area area = new Area().setName("test-area");
        mongoOperations.save(area);
        Country country = new Country().setName("test-country")
                .setArea(area);
        countryRepository.save(country);

        Assertions.assertThat(countryRepository.findAll()).hasSize(1);
        //count of areas should remain the same
        Assertions.assertThat(mongoOperations.findAll(Area.class)).hasSize(1);
    }

    @Test
    void shouldUpdateFieldsOfArea() {
        Area area = new Area().setName("test-area");
        mongoOperations.save(area);
        Country country = new Country().setName("test-country")
                .setArea(area.setName("updated-name"));
        countryRepository.save(country);
        Assertions.assertThat(mongoOperations.findAll(Area.class).get(0).getName()).isEqualTo("updated-name");
    }
}