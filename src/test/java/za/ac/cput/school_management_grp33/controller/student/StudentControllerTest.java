/**
 * @Author Curstin Rose - 220275408
 * StudentControllerTest.java
 * Created on 14 June 2022
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
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;
import za.ac.cput.school_management_grp33.factory.student.StudentFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentControllerTest {

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
    void addUpdateStudent() {
        Name name = NameFactory.build("Curstin", "Jade", "Rose");
        Student student = StudentFactory.build("220275408", "220275408@mycput.ac.za", name);
        ResponseEntity<Student> response = restTemplate.postForEntity(BASE_URL, student, Student.class);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void getStudentById() {
        String id = "220275408";
        ResponseEntity<Student> response = restTemplate.getForEntity(BASE_URL + id, Student.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void getAllStudents() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(BASE_URL, Student[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(4, response.getBody().length)
        );
    }

    @Test
    void deleteStudentById() {
        String id = "220275408";
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