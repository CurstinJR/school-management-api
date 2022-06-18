/*
Author: Kevin lionel Mombo Ndinga (218180500)
StudentAddressControllerTest.java;
 */
package za.ac.cput.school_management_grp33.controller.student;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.factory.student.StudentAddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentAddressControllerTest {

    @LocalServerPort
    private int PORT;
    private String BASE_URL;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        BASE_URL = "http://localhost:" + PORT + "/api/students/";
    }

    @Test
    @Order(4)
    void deleteStudentAddressById() {
        String id = "4";
        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + id + "/address",
                HttpMethod.DELETE,
                null,
                Void.class);
        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()),
                () -> assertNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void getStudentAddressById() {
        String id = "4";
        ResponseEntity<StudentAddress> response = restTemplate
                .getForEntity(BASE_URL + id + "/address", StudentAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void getAllStudentAddress() {
        ResponseEntity<StudentAddress[]> response = restTemplate
                .getForEntity(BASE_URL + "/address", StudentAddress[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(4, response.getBody().length)
        );
    }

    @Test
    @Order(1)
    void addUpdateStudentAddress() {
        City city = City.builder().id("1").build();
        Address address = new Address("450",
                "Georgia",
                "13",
                "Clifton",
                5000,
                city);
        StudentAddress studentAddress = StudentAddressFactory.build("4", address);
        ResponseEntity<StudentAddress> response = restTemplate.postForEntity(BASE_URL + "address",
                studentAddress, StudentAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    /**
     * Question 9
     *
     * @Author Curstin Rose - 220275408
     */
    @Test
    @Order(5)
    void getStudentsInCountry() {
        String id = "1";
        ResponseEntity<String[]> lastNames = restTemplate
                .getForEntity(BASE_URL + "address/country/" + id, String[].class);
        assertTrue(lastNames.getBody().length >= 3);
    }
}
