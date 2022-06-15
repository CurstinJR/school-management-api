/**
 * @Author Curstin Rose - 220275408
 * StudentFactoryTest.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.factory.student;

import com.sanctionco.jmail.InvalidEmailException;
import com.sanctionco.jmail.JMail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentFactoryTest {

    @DisplayName("Student test non-null values")
    @Test
    void buildNonNullValues() {
        Student student = StudentFactory.build("1", "joe@email.com", new Name());
        String studentId = student.getStudentId();
        String email = student.getEmail();
        assertEquals("1", studentId);
        assertEquals("joe@email.com", email);
    }

    @DisplayName("Student test null values")
    @Test
    void buildNullValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StudentFactory.build(null, null, new Name());
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @DisplayName("Valid student email")
    @Test
    void validStudentEmail() {
        Student student = StudentFactory.build("3", "joey13@email.com", new Name());
        assertTrue(JMail.isValid(student.getEmail()));
    }

    @DisplayName("Invalid student email")
    @Test
    void invalidStudentEmail() {
        Exception exception = assertThrows(InvalidEmailException.class, () -> {
            StudentFactory.build("4", "joey13@email.", new Name());
        });
        String expectedMessage = "EmailException";
        String actualMessage = exception.toString();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}