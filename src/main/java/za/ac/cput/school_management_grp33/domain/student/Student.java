/**
 * @Author Curstin Rose - 220275408
 * 13 June 2022
 * Student.java
 */
package za.ac.cput.school_management_grp33.domain.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sanctionco.jmail.JMail;
import lombok.*;
import org.hibernate.Hibernate;
import za.ac.cput.school_management_grp33.domain.lookup.Name;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Student {

    @Id
    @NotNull(message = "{validation.field.studentId.mandatory}")
    @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @NotNull(message = "{validation.field.email.mandatory}")
    @Email(groups = JMail.class)
    @Column(nullable = false, unique = true)
    private String email;

    @Embedded
    @NotNull(message = "{validation.field.Name.mandatory}")
    private Name name;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    @Transient
    @JsonIgnore
    private StudentAddress studentAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Student student = (Student) o;
        return studentId != null && Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
