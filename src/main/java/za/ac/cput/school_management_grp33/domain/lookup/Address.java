/*
Address.java
Author:Kevin Lionel Mombo Ndinga(218180500)
Date: 14 of June 2022;
 */
package za.ac.cput.school_management_grp33.domain.lookup;

import lombok.*;
import za.ac.cput.school_management_grp33.domain.location.City;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Address {

    private String unitNumber;
    private String complexName;
    private String streetNumber;
    private String streetName;
    private int postalCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return postalCode == address.postalCode
                && Objects.equals(unitNumber, address.unitNumber)
                && Objects.equals(complexName, address.complexName)
                && Objects.equals(streetNumber, address.streetNumber)
                && Objects.equals(streetName, address.streetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitNumber, complexName, streetNumber, streetName, postalCode);
    }
}
