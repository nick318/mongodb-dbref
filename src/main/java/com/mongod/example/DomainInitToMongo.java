package com.mongod.example;

import com.mongod.example.db.CountryRepository;
import com.mongod.example.domain.Area;
import com.mongod.example.domain.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomainInitToMongo implements ApplicationListener<ApplicationStartedEvent> {

    private final CountryRepository countryRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        if (countryRepository.findAll().isEmpty()) {
            Area emea = new Area().setName("EMEA");
            Country russia = new Country().setName("Russia").setArea(emea);
            Country estonia = new Country().setName("Estonia").setArea(emea);
            countryRepository.save(russia);
            countryRepository.save(estonia);
        }
    }
}
