/**
        * @Author Ngonidzaishe Erica Chipato
        * 218327315
        * 17 June 2022
        * EmployeeAddressControllerTest.java
        */


        package za.ac.cput.school_management_grp33.controller.employee;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeAddressFactory;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class EmployeeAddressControllerTest {

    @LocalServerPort
    private int PORT;
    private String BASE_URL;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp()
    {
        BASE_URL = "http://localhost:" + PORT + "/api/employees/address";
    }

    @Test
    @Order(1)
    void addUpdateEmployeeAddress()
    {
       Address address = AddressFactory.build("50","OceanTides","35",
               "487","Sand-ville",8002,new City());
       EmployeeAddress employeeAddress = EmployeeAddressFactory.build("220789451",address);
        ResponseEntity<EmployeeAddress> response = restTemplate.postForEntity(BASE_URL, employeeAddress,
                EmployeeAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(2)
    void getEmployeeAddressById()
    {
        String id = "220789451";
        ResponseEntity<EmployeeAddress> response = restTemplate.getForEntity(BASE_URL + id, EmployeeAddress.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }

    @Test
    @Order(3)
    void getAllEmployeesAddress()
    {
        ResponseEntity<EmployeeAddress[]> response = restTemplate.getForEntity(BASE_URL, EmployeeAddress[].class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(4, response.getBody().length)
        );
    }


    @Test
    @Order(4)
    void deleteEmployeeAddressById() {
        String id = "220789451";
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