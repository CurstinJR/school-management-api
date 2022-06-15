/**
 * @Author Curstin Rose - 220275408
 * StudentController.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.exception.EmailExistsException;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.student.impl.StudentServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    public static final String STUDENT_WITH_ID_NOT_FOUND_MSG = "Student with id: %s not found";
    public static final String STUDENT_WITH_EMAIL_NOT_FOUND_MSG = "Student with email: %s not found";
    public static final String STUDENT_EMAIL_EXISTS_MSG = "Student email exists: %s";
    private final StudentServiceImpl studentService;

    @Autowired
    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        String notFoundMessage = String.format(STUDENT_WITH_ID_NOT_FOUND_MSG, id);
        Student student = studentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<?> addUpdateStudent(@Valid @RequestBody Student student) {
        boolean existsByEmail = studentService.existsByEmail(student.getEmail());
        if (existsByEmail) {
            String emailExistsMessage = String.format(STUDENT_EMAIL_EXISTS_MSG, student.getEmail());
            throw new EmailExistsException(emailExistsMessage);
        }
        Student saveStudent = studentService.save(student);
        return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {
        if (!studentService.existsById(id)) {
            String notFoundMessage = String.format(STUDENT_WITH_ID_NOT_FOUND_MSG, id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
