/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 14 June 2022
 * StudentAddress.java
 */



package za.ac.cput.school_management_grp33.domain.student;


import za.ac.cput.school_management_grp33.domain.lookup.Address;
import lombok.*;
import org.hibernate.Hibernate;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class StudentAddress {

    @Id
    @NotNull
    @Column(nullable = false)
    private String studentId;

    @Id
    @NotNull
    @Column(nullable = false)
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentAddress studentAddress = (StudentAddress) o;
        return studentId != null && Objects.equals(studentId, studentAddress.studentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();


    }

}
