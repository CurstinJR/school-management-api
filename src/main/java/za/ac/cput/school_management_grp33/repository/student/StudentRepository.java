/**
 * @Author Curstin Rose - 220275408
 * StudentRepository.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.school_management_grp33.domain.student.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findStudentByEmail(String email);

    boolean existsByEmail(String email);

}
