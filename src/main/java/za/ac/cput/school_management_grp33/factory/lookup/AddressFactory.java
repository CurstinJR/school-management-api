package za.ac.cput.school_management_grp33.factory.lookup;

import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.util.Util;
/*
Author:Kevin Lionel Mombo Ndinga (218180500)
AddressTest.java
 */
public class AddressFactory {
    public static Address build(String unitNumber, String complexName, String complexNumber, String streetNumber,
                                String streetName, int postalCode, City city) {

        Util.checkStringParam("unitNumber",unitNumber);
        Util.checkStringParam("complexName",complexName);
        Util.checkStringParam("complexNumber",complexNumber);
        Util.checkStringParam("streetNumber",streetNumber);
        Util.checkStringParam("streetName",streetName);
        Util.checkRangeNum(postalCode);

         return Address.builder().unitNumber(unitNumber).complexName(complexName)
                 .complexNumber(complexNumber)
                 .streetNumber(streetNumber)
                 .streetName(streetName )
                 .city(city).build();
    }

}

