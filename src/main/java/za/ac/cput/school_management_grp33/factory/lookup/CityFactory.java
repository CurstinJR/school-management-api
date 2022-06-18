package za.ac.cput.school_management_grp33.factory.lookup;

import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;

public class CityFactory {
    public static City getCity(String id, String name, Country country) {
        return City.builder()
                .id(id)
                .name(name)
                .country(country).build();
    }
}
