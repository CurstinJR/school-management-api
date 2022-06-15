/**
 * @Author Curstin Rose - 220275408
 * StudentServiceImpl.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.student.StudentFactory;
import za.ac.cput.school_management_grp33.repository.student.StudentRepository;
import za.ac.cput.school_management_grp33.service.student.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * The save() adds a newStudent to the repository and if the newStudent
     * exists in the repository it will update the newStudent's information.
     *
     * @param newStudent Student
     * @return Student
     */
    @Override
    public Student save(Student newStudent) {
        String id = newStudent.getStudentId();
        return findById(id).map(student -> {
            String studentId = student.getStudentId();
            String email = newStudent.getEmail();
            Name name = newStudent.getName();
            student = StudentFactory.build(studentId, email, name);
            return studentRepository.save(student);
        }).orElseGet(() -> {
            String studentId = newStudent.getStudentId();
            String email = newStudent.getEmail();
            Name name = newStudent.getName();
            return studentRepository.save(StudentFactory.build(studentId, email, name));
        });
    }

    /**
     * @return List of Students
     */
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    /**
     * The findById() finds the student by the identifier.
     *
     * @param id String
     * @return Non-null Student
     */
    @Override
    public Optional<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    /**
     * The findStudentByEmail() finds the student by the email address.
     *
     * @param email String
     * @return Non-null Student
     */
    @Override
    public Optional<Student> findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    /**
     * Checks if Student email is in the repository. It will return true if student email exists
     * otherwise false.
     *
     * @param email String
     * @return boolean
     */
    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    /**
     * Checks if Student ID is in the repository. It will return true if student ID exists
     * otherwise false.
     *
     * @param id String
     * @return boolean
     */
    @Override
    public boolean existsById(String id) {
        return studentRepository.existsById(id);
    }

    /**
     * To delete() removes the student from the repository.
     *
     * @param id String
     */
    @Override
    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }

}
