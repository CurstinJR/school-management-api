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
/*
Author: Kevin lionel Mombo Ndinga (218180500)
StudentAddressControllerTest.java;
 */
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
    @Order(1)
    void deleteStudentAddressById() {
        String id = "218180466";
        ResponseEntity<Void> response = restTemplate.exchange(BASE_URL + id,
                HttpMethod.DELETE,
                null,
                Void.class);
        assertAll(
                () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()),
                () -> assertNull(response.getBody())
        );
        @Test
        @Order(2)
        void getStudentAddressById () {
            String id = "22014786";
            ResponseEntity<StudentAddress> response = restTemplate.getForEntity(BASE_URL + id, StudentAddress.class);
            assertAll(
                    () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                    () -> assertNotNull(response.getBody())
            );
            @Test
            @Order(3)
            void getAllStudentAddress () {
                ResponseEntity<StudentAddress[]> response = restTemplate.getForEntity(BASE_URL, StudentAddress[].class);
                assertAll(
                        () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                        () -> assertEquals(4, response.getBody().length)
                );
            }
            @Test
            @Order(4)
            void addUpdateStudentAddress () {
                StudentAddress studentAddress = StudentAddressFactory.build("120", new Address("450",
                        "Georgia", "13", "Clifton", 5000, new City()));
                StudentAddress studentAddress = StudentAddressFactory.build("785", new Address("250",
                        "Leopold2", "Gregoria", "Dallas", 9200, new City());
                ResponseEntity<StudentAddress> response = restTemplate.postForEntity(BASE_URL, studentAddress, StudentAddress.class);
                assertAll(
                        () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                        () -> assertNotNull(response.getBody())
                );
            }
        }
    }
}