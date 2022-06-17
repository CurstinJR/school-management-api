package za.ac.cput.school_management_grp33.factory.location;

import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.util.StringUtility;

public class CityFactory {
    public static City getCity(String id, String name, Country country) {
        StringUtility.checkStringParam("id", id);
        StringUtility.checkStringParam("name", name);
        country = CountryFactory.build(country.getId(), country.getName());
        return City.builder()
                .id(id)
                .name(name)
                .country(country).build();
    }
}
