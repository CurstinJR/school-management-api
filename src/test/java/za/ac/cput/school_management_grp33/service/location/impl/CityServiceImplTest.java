package za.ac.cput.school_management_grp33.service.location.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;
import za.ac.cput.school_management_grp33.factory.lookup.CityFactory;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    private CityServiceImpl service;
    @Autowired
    private CountryRepository countryRepository;
    @Test
    void getCityService() {
    }

    @Test
    void save() {
        Country country = CountryFactory.
        City city = CityFactory.getCity("1230","Brazaville", Country.builder().id("123").name("1ongo").build());
        //City city = CityFactory.getCity("1230","Brazaville", new Country());
        City city1 = service.save(city);
        assertNotNull(city1);
    }

    @Test
    void findAll() {
        List<City> city1 = service.findAll();
        System.out.println(city1);
    }

    @Test
    void findById() {
        City city = service.findById("").get();
        assertNotNull(city);
    }

    @Test
    void deleteById() {
    }

    @Test
    void getEmployeesLiving() {
    }
}