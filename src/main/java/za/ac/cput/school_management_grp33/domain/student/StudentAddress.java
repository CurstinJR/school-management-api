/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 15 June 2022
 * EmployeeAddressFactory.java
 */
package za.ac.cput.school_management_grp33.domain.student;

import lombok.*;
import org.hibernate.Hibernate;
import za.ac.cput.school_management_grp33.domain.lookup.Address;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "student_address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class StudentAddress implements Serializable {

    @Id
    @Column(name = "student_id")
    private String id;

    @Embedded
    private Address address;

    @OneToOne
    @MapsId
    @JoinColumn(name = "student_id")
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StudentAddress studentAddress = (StudentAddress) o;
        return id != null && Objects.equals(id, studentAddress.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
