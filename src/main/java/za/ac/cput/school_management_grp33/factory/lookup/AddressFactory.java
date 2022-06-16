package za.ac.cput.school_management_grp33.factory.lookup;

import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.util.StringUtility;
import za.ac.cput.school_management_grp33.util.Utils;


/*
Author:Kevin Lionel Mombo Ndinga (218180500)
AddressTest.java
 */
public class AddressFactory {
    public static Address build(String unitNumber, String complexName, String streetNumber,
                                String streetName, int postalCode, City city) {


        StringUtility.checkStringParam("unitNumber", unitNumber);
        StringUtility.checkStringParam("complexName", complexName);
        StringUtility.checkStringParam("streetNumber", streetNumber);
        StringUtility.checkStringParam("streetName", streetName);
        Utils.checkRangeNum(postalCode, 1000, 9000);

        return Address.builder().unitNumber(unitNumber).complexName(complexName)
                .streetNumber(streetNumber)
                .streetName(streetName)
                .city(city).build();

    }

}

