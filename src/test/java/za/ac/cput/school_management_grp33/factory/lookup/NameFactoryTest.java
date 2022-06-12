package za.ac.cput.school_management_grp33.factory.lookup;

import org.junit.jupiter.api.Test;
import za.ac.cput.school_management_grp33.domain.lookup.Name;


import static org.junit.jupiter.api.Assertions.*;

class NameFactoryTest {

    @Test public void build() {
        // this test will throw an Illegal argument exception
        Name name = NameFactory.build("Kevin", null, "Mombo");
        System.out.println(name);
        assertNotNull(name);
    }
    @Test
    void buildworks() {

        Name name2 = NameFactory.build("Spencer", "Jason", "Fortuin");
        System.out.println(name2);
        assertNotNull(name2);
    }
}