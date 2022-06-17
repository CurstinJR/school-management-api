package za.ac.cput.school_management_grp33.domain.student;

import lombok.*;
import za.ac.cput.school_management_grp33.domain.lookup.Address;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentAddress {
    @Id
    private String studentId;
    @Embedded
    private Address address;
}
