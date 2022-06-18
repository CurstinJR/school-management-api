/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 17 June 2022
 * EmployeeAddressControllerTest.java
 */
package za.ac.cput.school_management_grp33.factory.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

class StudentAddressFactoryTest {

    private Address address;

    @BeforeEach
    void setUp() {
        address = AddressFactory.build("50", "Houtbay", "55",
                "Bayside", 4002, new City());
    }

    @Test
    void buildNonNullValues() {
        StudentAddress studentAddress = StudentAddressFactory.build("845", address);
        String staffId = studentAddress.getStudentId();
        Address address = studentAddress.getAddress();
        assertEquals("845", staffId);
        assertEquals("Bayside", address.getStreetName());
    }

    @Test
    void buildNullValues1() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            StudentAddressFactory.build("1", null);
        });
        String expectedMessage = "\"address\" is null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void buildNullValues2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentAddressFactory.build(null, address);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}