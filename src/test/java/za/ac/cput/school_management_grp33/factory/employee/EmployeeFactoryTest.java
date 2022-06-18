/**
 * @Author Curstin Rose - 220275408
 * EmployeeFactoryTest.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.factory.employee;

import com.sanctionco.jmail.InvalidEmailException;
import com.sanctionco.jmail.JMail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFactoryTest {

    private Name name;

    @BeforeEach
    void setUp() {
        name = NameFactory.build("Joe", "Prince", "Roy");
    }

    @DisplayName("Employee test non-null values")
    @Test
    void buildNonNullValues() {
        Employee employee = EmployeeFactory.build("1", "joe@email.com", name);
        String staffId = employee.getStaffId();
        String email = employee.getEmail();
        assertEquals("1", staffId);
        assertEquals("joe@email.com", email);
    }

    @DisplayName("Employee test null values")
    @Test
    void buildNullValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EmployeeFactory.build(null, null, name);
            EmployeeFactory.build(null, null, name);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("Valid employee email")
    @Test
    void validEmployeeEmail() {
        Employee employee = EmployeeFactory.build("3", "joey13@email.com", name);
        assertTrue(JMail.isValid(employee.getEmail()));
    }

    @DisplayName("Invalid employee email")
    @Test
    void invalidEmployeeEmail() {
        Exception exception = assertThrows(InvalidEmailException.class, () -> {
            EmployeeFactory.build("4", "joey13@email.", name);
            EmployeeFactory.build("4", "joey13@email.", name);
        });
        String expectedMessage = "EmailException";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
