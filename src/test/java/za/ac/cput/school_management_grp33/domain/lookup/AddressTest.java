package za.ac.cput.school_management_grp33.domain.lookup;

import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;

import static org.junit.jupiter.api.Assertions.*;
/*
Author: Kevin Lionel Mombo Ndinga (218180500)
AddressTest.java
 */
class AddressTest {

    @Test
    void testingAddressOne() {
        Address address = AddressFactory.build("500","Thelighter","15",
                "95","playa",100,new City());
        assertFalse(1000<= address.getPostalCode() && address.getPostalCode() <=9999);

    }
    @Test
    void testingAddressTwo() {
        Address address2 = AddressFactory.build("150", "Greenpack", "20",
                "92", "AvonMonth", 1200, new City());
        assertFalse(1000<= address2.getPostalCode() && address2.getPostalCode() <=9999);
    }
    @Test
    void testingAddressThree() {
        Address address3 = AddressFactory.build("475", "AvonSands", "20",
                "55", "AvonMonth", 9500, new City());
        assertFalse(1000<= address3.getPostalCode() && address3.getPostalCode() <=9999);
    }
}