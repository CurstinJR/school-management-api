package za.ac.cput.school_management_grp33.domain.lookup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/*
Author:Kevin Lionel Mombo Ndinga(218180500)
AddressTest.java
 */
class AddressTest {

    @Test
    public void test() {
        Address address1 = new Address.AddressBuilder()
                .unitNumber("475")
                .complexName("Avon Sands")
                .streetNumber("Scott")
                .streetName("Scott")
                .postalCode(2500)
               /*
               .city() // need the city class to be completed to work
                */
                .build();
        System.out.println(address1);
    }
}