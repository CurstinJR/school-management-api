/**
 * @Author Curstin Rose - 220275408
 * StudentService.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.service.student;

import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.service.IService;

import java.util.Optional;

public interface StudentService extends IService<Student, String> {

    Optional<Student> findStudentByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(String id);

}
