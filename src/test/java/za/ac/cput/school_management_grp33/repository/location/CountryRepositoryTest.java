package za.ac.cput.school_management_grp33.repository.location;
/*
CountryRepositoryTest.java
Author: Tarren-Marc Adams - 214041794
Date: 17 March 2022
 */

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class CountryRepositoryTest {
    private final Country country = CountryFactory.build("25", "Australia");
    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Order(1)
    void save() {
        Country saveCountry = countryRepository.save(country);
        Country exist = countryRepository.findById(saveCountry.getId()).orElseThrow();
        assertEquals(country.getId(), exist.getId());
    }

    @Test
    @Order(2)
    void findAll() {
        List<Country> countries = countryRepository.findAll();
        assertTrue(countries.size() >= 1);
    }

    @Test
    @Order(3)
    void findById() {
        String id = country.getId();
        Country exist = countryRepository.findById(id).orElseThrow();
        assertEquals(exist.getId(), country.getId());
    }


    @Test
    @Order(4)
    void deleteById() {
        String id = country.getId();
        Country exist = countryRepository.findById(id).orElseThrow();
        countryRepository.deleteById(exist.getId());
        boolean isCountryThere = countryRepository.findById(id).isPresent();
        assertFalse(isCountryThere);
    }

}