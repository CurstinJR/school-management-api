package za.ac.cput.school_management_grp33.factory.lookup;

import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.lookup.Name;

import static org.junit.jupiter.api.Assertions.*;

/*
Author:Kevin Lionel Mombo Ndinga (218180500)
NameFactoryTest.java;
 */
class NameFactoryTest {

    @Test
    void buildNullValues() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                NameFactory.build(null, "KevinMombo", null));
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void buildWorks() {
        Name name2 = NameFactory.build("Spencer", "Jason", "Fortuin");
        System.out.println(name2);
        assertNotNull(name2);
    }
}