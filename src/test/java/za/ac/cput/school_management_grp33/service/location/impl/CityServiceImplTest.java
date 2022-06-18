/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * CityServiceImplTest.java
 */
package za.ac.cput.school_management_grp33.service.location.impl;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CityFactory;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CityServiceImplTest {

    @Autowired
    private CityServiceImpl service;
    @Autowired
    private CountryRepository countryRepository;

    @Test
    void a_save() {
        Country country = Country.builder().name("Tanzania").id("1234").build();
        Country country1 = countryRepository.save(country);
        System.out.println(country1);
        City city = CityFactory.getCity("1230", "Brazaville", country);
        City city1 = service.save(city);
        assertNotNull(city1);
    }

    @Test
    void b_findAll() {
        List<City> city1 = service.findAll();
        assertNotNull(city1);
        System.out.println(city1);
    }

    @Test
    void c_findById() {
        City city = service.findById("1230").orElse(null);
        assertNotNull(city);
        System.out.println(city);
    }

    @Test
        // @Disabled
    void deleteById() {
        service.deleteById("1230");
        City city = service.findById("1230").orElse(null);
        assertNull(city);
    }
}
