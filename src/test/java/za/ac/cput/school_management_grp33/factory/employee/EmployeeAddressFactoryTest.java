/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 17 June 2022
 * EmployeeAddressFactoryTest.java
 */
package za.ac.cput.school_management_grp33.factory.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeAddressFactoryTest {
    private Address address;

    @BeforeEach
    void setUp() {
        address = AddressFactory.build("50", "Baliview", "35",
                "Hilltop", 1002, new City());
    }

    @Test
    void buildNonNullValues() {
        EmployeeAddress employeeAddress = EmployeeAddressFactory.build("215", address);
        String staffId = employeeAddress.getStaffId();
        String streetName = employeeAddress.getAddress().getStreetName();
        assertEquals("215", staffId);
        assertEquals("Hilltop", streetName);
    }

    @Test
    void buildNullValues1() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            EmployeeAddressFactory.build("1", null);
        });
        String expectedMessage = "\"address\" is null";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void buildNullValues2() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeAddressFactory.build(null, address);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}