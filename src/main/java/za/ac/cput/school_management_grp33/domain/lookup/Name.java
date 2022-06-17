/*
Name.java
Author: Kevin Lionel Mombo Ndinga(218180500)
Date: 12 of June 2022;
 */
package za.ac.cput.school_management_grp33.domain.lookup;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Name {
    private String firstName;
    private String middleName;
    private String lastName;
}

