/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.controller.location;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.location.CityFactory;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeAddressRepository;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeRepository;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;
import za.ac.cput.school_management_grp33.service.location.impl.CityServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CityControllerTest {

    Country country = CountryFactory.build("122", "Congo");
    City cityObject = CityFactory.getCity("123", "Kinshasa", country);
    @LocalServerPort
    private int PORT;
    private String baseUrl;
    @Autowired
    private CityServiceImpl service;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeAddressRepository employeeAddressRepository;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + PORT + "/api/cities/";
    }

    @Test
    void a_createUpdate() {
        this.countryRepository.save(country);
        ResponseEntity<City> response = restTemplate.postForEntity(baseUrl, cityObject, City.class);
        assertNotNull(response.getBody());
    }

    @Test
    void b_read() {
        ResponseEntity<City> response = restTemplate.getForEntity(baseUrl + cityObject.getId(), City.class);
        assertNotNull(response.getBody());
    }

    @Test
    void c_readAll() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + cityObject.getId(), String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void d_delete() {
        restTemplate.delete(baseUrl + cityObject.getId(), String.class);
        ResponseEntity<City> response = restTemplate.getForEntity(baseUrl + cityObject.getId(), City.class);
        assertNull(response.getBody().getId());
    }
    @Test
    void e_findEmployeesByCityId(){
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

        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl+"employees/" + city1.getId(), String.class);
        assertNotNull(response.getBody());
    }

}
