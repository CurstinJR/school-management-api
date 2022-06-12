package za.ac.cput.school_management_grp33.domain.lookup;

import org.junit.jupiter.api.Test;
/*
NameTest.java
Domain entity test
Author: Kevin Lionel Mombo Ndinga
Date: 12 of June 2022;
 */
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Test
    public void test() {
        Name name1 = new Name.NameBuilder()
                .firstName("Trevor")
                .middleName("Jason ")
                .lastName("Scott")
                .build();
        System.out.println(name1);

        Name name2 = new Name.NameBuilder()
                .firstName("Rusty")
                .middleName("Adecel")
                .lastName("Mabiala")
                .build();
        System.out.println(name2);

        Name name3 = new Name.NameBuilder()
                .firstName("Richard")
                .middleName("Albert")
                .lastName("Spencer")
                .build();
        System.out.println(name3);
    }
}