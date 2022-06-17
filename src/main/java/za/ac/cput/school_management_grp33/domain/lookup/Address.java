/*
Address.java
Author:Kevin Lionel Mombo Ndinga(218180500)
Date: 14 of June 2022;
 */
package za.ac.cput.school_management_grp33.domain.lookup;

import lombok.*;
import za.ac.cput.school_management_grp33.domain.location.City;

import javax.persistence.Embedded;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    @Embedded
    private City city;
}
