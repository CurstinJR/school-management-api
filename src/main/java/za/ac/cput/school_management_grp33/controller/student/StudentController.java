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

    /**
     * Handles the request to retrieve all student and return an array of
     * student objects.
     *
     * @return List of student objects
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.findAll();
        return ResponseEntity.ok(students);
    }

    /**
     * Handles the request to retrieve a specific Student object by providing
     * an ID. Throws 404 NOT_FOUND, if Student object is not in the repository.
     *
     * @param id String
     * @return 200 and Student object SUCCESS
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id) {
        String notFoundMessage = String.format(STUDENT_WITH_ID_NOT_FOUND_MSG, id);
        Student student = studentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(student);
    }

    /**
     * Handles the request to create a new Student object or update an existing
     * Student object. Throws 400 BAD_REQUEST, if payload is malformed.
     * Throws 500 INTERNAL_SERVER_ERROR, if request can not be processed.
     *
     * @param student Student JSON payload
     * @return 201 and new Student object
     */
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

    /**
     * Handles the request to delete a Student object from the repository.
     * Throws 404 NOT_FOUND, if Student object is not in the repository.
     *
     * @param id String
     * @return 204 No content
     */
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
