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
        address = AddressFactory.build("50","Baliview","35",
                "487","Hilltop",1002,new City());
    }

    @Test
    void buildNonNullValues(){
       EmployeeAddress employeeAddress = EmployeeAddressFactory.build("215", address);
        String staffId = employeeAddress.getStaffId();
        Address address = employeeAddress.getAddress();
        assertEquals("215", staffId);
        assertEquals("Cape Town", address);
    }
    @Test
    void buildNullValues1(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           EmployeeAddressFactory.build(null, null);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void buildNullValues2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeAddressFactory.build("215", null);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}