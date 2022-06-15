package za.ac.cput.school_management_grp33.factory.location;

import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.location.Country;

import static org.junit.jupiter.api.Assertions.*;

class CountryFactoryTest {
    @Test
    void buildNonNullValues(){
        Country country = CountryFactory.build("1", "South-Africa");
        String id = country.getId();
        String name = country.getName();
        assertEquals("1", id);
        assertEquals("South-Africa", name);
    }
    @Test
    void buildNullValues1(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CountryFactory.build(null, null);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void buildNullValues2(){
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CountryFactory.build("1", null);
        });
        String expectedMessage = "Invalid value for param:";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}