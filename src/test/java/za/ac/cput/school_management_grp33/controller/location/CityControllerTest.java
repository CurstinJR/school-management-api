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
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;
import za.ac.cput.school_management_grp33.factory.lookup.CityFactory;
import za.ac.cput.school_management_grp33.repository.location.CountryRepository;

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
    private TestRestTemplate restTemplate;
    @Autowired
    private CountryRepository countryRepository;

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
    void readAll() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + cityObject.getId(), String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void delete() {
        restTemplate.delete(baseUrl + cityObject.getId(), String.class);
        ResponseEntity<City> response = restTemplate.getForEntity(baseUrl + cityObject.getId(), City.class);
        assertNull(response.getBody().getId());
    }
}
