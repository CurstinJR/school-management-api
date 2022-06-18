/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.service.location.impl;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.location.CityFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeAddressRepository;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeRepository;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CityServiceImplTest {

    @Autowired
    private CityServiceImpl service;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAddressRepository employeeAddressRepository;

    @Test
    void getCityService() {
    }

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
    void deleteById() {
        service.deleteById("1230");
        City city = service.findById("").orElse(null);
        assertNull(city);
    }

    @Test
    void getEmployeesLiving() {
        Country country = Country.builder().name("Tanzania").id("1234").build();
        Country country1 = countryRepository.save(country);
        System.out.println(country1);
        City city = CityFactory.getCity("1230", "Brazaville", country);
        City city1 = service.save(city);
        System.out.println("City created sucessfully: "+city1);

        Name name = Name.builder().firstName("Max").lastName("Well").middleName("Robert").build();
        Employee employee = Employee.builder().name(name).email("max@well.com").staffId("0001").build();
        Employee employeResult = employeeRepository.save(employee);
        System.out.println("employee :"+employeResult);

        Address address = Address.builder().city(city).complexName("Butros").streetName("Reebieck").streetNumber("20").postalCode(6739).build();
        EmployeeAddress employeeAddress = EmployeeAddress.builder().address(address).staffId("0001").build();
        EmployeeAddress employeeAddress1 = employeeAddressRepository.save(employeeAddress);
        System.out.println("employeeAddress :"+employeeAddress1);

        List<Name> names = service.getEmployeesIn("1230");
        System.out.println("Employees in the city are :"+names);
        assertNotNull(names);
    }
}