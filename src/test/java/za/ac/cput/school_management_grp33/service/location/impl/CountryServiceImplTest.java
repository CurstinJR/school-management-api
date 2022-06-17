package za.ac.cput.school_management_grp33.service.location.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

/*
CountryServiceImpTest.java
Author: Tarren-Marc Adams - 214041794
Date: 16 March 2022
 */

@ExtendWith(MockitoExtension.class)
class CountryServiceImplTest {

    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CountryServiceImpl service;

    private  Country country1,country2,country3;
    private List<Country> countries;

    @BeforeEach
    void setup(){
        countries = new ArrayList<>();
        country1 = CountryFactory.build("1", "South-Africa");
        country2 = CountryFactory.build("10","Kenya");
        country3 = CountryFactory.build("15", "Nigeria");
        countries.add(country1);
        countries.add(country2);
        countries.add(country3);
    }

    @AfterEach
    void teardown(){
        country1 = null;
        country2 = null;
        country3 = null;
    }

    @Test
    void save(){
        given(repository.save(any(Country.class))).willReturn(country1);
        Country country = service.save(country1);
        assertNotNull(country);
    }

    @Test
    void findAll(){
        repository.save(country2);
        repository.save(country3);
        given(repository.findAll()).willReturn(countries);
        List<Country> countryList = service.findAll();
        assertAll(
                () -> assertNotNull(countryList),
                () -> assertTrue(countryList.size() >= 3),
                () -> assertEquals(countryList,countries)
        );
    }

    @Test
    void findById() {
        given(repository.findById("15")).willReturn(Optional.of(country3));
        Country country = service.findById(country3.getId()).orElseThrow();
        assertNotNull(country);
        assertAll(
                () -> assertEquals("15", country.getId())
        );
    }
    @Test
    void deleteById() {
        String id = "10";
        service.deleteById(country2.getId());
        assertFalse(repository.existsById(id));
    }
}