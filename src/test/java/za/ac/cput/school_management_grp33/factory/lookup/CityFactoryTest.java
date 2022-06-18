package za.ac.cput.school_management_grp33.factory.lookup;

import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityFactoryTest {

    @Test
    void getCity() {
        City city = CityFactory.getCity("1234", "DODOMA", new Country());
        System.out.println(city.toString());
        assertEquals(city.getId(), "1234");
    }
}