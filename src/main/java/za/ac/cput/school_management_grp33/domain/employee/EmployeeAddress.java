/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 14 June 2022
 * EmployeeAddress.java
 */
package za.ac.cput.school_management_grp33.domain.employee;

import lombok.*;
import org.hibernate.Hibernate;
import za.ac.cput.school_management_grp33.domain.lookup.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "employee_address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class EmployeeAddress {

    @Id
    @NotNull
    @Column(nullable = false)
    private String staffId;

    @Embedded
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeAddress employeeAddress = (EmployeeAddress) o;
        return staffId != null && Objects.equals(staffId, employeeAddress.staffId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}