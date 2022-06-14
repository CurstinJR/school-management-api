package za.ac.cput.school_management_grp33.domain.lookup;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import za.ac.cput.school_management_grp33.domain.location.City;

/*
Address.java
Author:Kevin Lionel Mombo Ndinga(218180500)
Date: 14 of June 2022;
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    private String unitNumber;
    private String complexName;
    private String streetNumber;
    private String streetName;
    private int postalCode;
   private City city;
}
