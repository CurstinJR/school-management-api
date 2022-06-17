package za.ac.cput.school_management_grp33.controller.location;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.location.CountryFactory;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CountryControllerTest {

    @LocalServerPort
    private int PORT;
    private String BASE_URL;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        BASE_URL = "http://localhost:" + PORT + "/api/countries/";
    }

    @Test
    @Order(1)
    void addUpdateCountry() {
        Country country = CountryFactory.build("12", "South-Africa");
        ResponseEntity<Country> response = restTemplate.postForEntity(BASE_URL, country, Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }
    @Test
    @Order(2)
    void getCountryId() {
        String id = "12";
        ResponseEntity<Country> response = restTemplate.getForEntity(BASE_URL + id, Country.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void getAllCountries() {
        ResponseEntity<Country[]> response = restTemplate.getForEntity(BASE_URL, Country[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(3, response.getBody().length)
        );
    }

    @Test
    void deleteStudentId() {
        String id = "12";
        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + id,
                HttpMethod.DELETE,
                null,
                Void.class);
        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()),
                () -> assertNull(response.getBody())
        );
    }

}