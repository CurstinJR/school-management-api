/**
 * @Author Curstin Rose - 220275408
 * Employee.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.domain.employee;

import com.sanctionco.jmail.JMail;
import lombok.*;
import org.hibernate.Hibernate;
import za.ac.cput.school_management_grp33.domain.lookup.Name;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @NotNull(message = "{validation.field.staffId.mandatory}")
    @Column(nullable = false)
    private String staffId;

    @NotNull(message = "{validation.field.email.mandatory}")
    @Email(groups = JMail.class)
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    @NotNull(message = "{validation.field.Name.mandatory}")
    private Name name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return staffId != null && Objects.equals(staffId, employee.staffId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
