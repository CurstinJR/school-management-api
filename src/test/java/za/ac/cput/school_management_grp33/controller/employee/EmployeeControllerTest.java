package za.ac.cput.school_management_grp33.controller.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeFactory;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class EmployeeControllerTest {

    @LocalServerPort
    private int PORT;
    private String BASE_URL;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        BASE_URL = "http://localhost:" + PORT + "/api/employees/";
    }

    @Test
    @Order(1)
    void addUpdateEmployee() {
        Name name = NameFactory.build("Curstin", "Jade", "Rose");
        Employee employee = EmployeeFactory.build("220275408", "220275408@mycput.ac.za", name);
        ResponseEntity<Employee> response = restTemplate.postForEntity(BASE_URL, employee, Employee.class);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void getEmployeeById() {
        String id = "220275408";
        ResponseEntity<Employee> response = restTemplate.getForEntity(BASE_URL + id, Employee.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void getAllEmployees() {
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(BASE_URL, Employee[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(4, response.getBody().length)
        );
    }

    @Test
    @Order(4)
    void deleteEmployeeById() {
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