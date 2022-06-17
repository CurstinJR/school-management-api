/*
CountryFactory.java
Author: Tarren-Marc Adams - 214041794
Date: 15 March 2022
 */
package za.ac.cput.school_management_grp33.factory.location;

import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.util.StringUtility;

public class CountryFactory {
    public static Country build(String id, String name) {
        StringUtility.checkStringParam("id", id);
        StringUtility.checkStringParam("name", name);
        return Country.builder()
                .id(id)
                .name(name)
                .build();
    }
}
