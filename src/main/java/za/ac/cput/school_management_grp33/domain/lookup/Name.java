package za.ac.cput.school_management_grp33.domain.lookup;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/*
Name.java
Author: Kevin Lionel Mombo Ndinga(218180500)
Date: 12 of June 2022;
 */
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Name {
    private String firstName, middleName, lastName;
    
}