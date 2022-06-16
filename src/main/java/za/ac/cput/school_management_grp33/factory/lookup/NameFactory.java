package za.ac.cput.school_management_grp33.factory.lookup;

import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.util.StringUtility;


/*
NameFactory.java;
Author: Kevin Lionel Mombo Ndinga (218180500);
Date: 12 of june 2022;
 */
public class NameFactory {
    public static Name build(String firstName, String middleName, String lastName) {

        StringUtility.checkStringParam("firstName", firstName);
        StringUtility.setEmptyIfNull(middleName);
        StringUtility.checkStringParam("lastName", lastName);


        return Name.builder().firstName(firstName).middleName(middleName).
                lastName(lastName).build();
    }
}
