/**
 * @Author Curstin Rose - 220275408
 * StudentRepositoryTest.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.repository.student;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.student.StudentFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    private final Student student = StudentFactory.build("100", "joe13@email.com", new Name());

    @Test
    @Order(1)
    void save() {
        Student saveStudent = studentRepository.save(student);
        Student existUser = studentRepository.findById(saveStudent.getStudentId()).orElseThrow();
        assertEquals(student.getStudentId(), existUser.getStudentId());
    }

    @Test
    @Order(2)
    void findAll() {
        // there are 3 students in the database
        // get all students from repository including the newly saved student
        List<Student> students = studentRepository.findAll();
        assertTrue(students.size() >= 4);
    }

    @Test
    @Order(3)
    void findById() {
        String id = student.getStudentId();
        // find the newly saved student by id
        Student existStudent = studentRepository.findById(id).orElseThrow();
        assertEquals(existStudent.getEmail(), student.getEmail());
    }

    @Test
    @Order(4)
    void findStudentByEmail() {
        String email = student.getEmail();
        // find the newly saved student by email
        Student existStudent = studentRepository.findStudentByEmail(email).orElseThrow();
        assertEquals(existStudent.getEmail(), student.getEmail());
    }

    @Test
    @Order(5)
    void deleteById() {
        String id = student.getStudentId();
        Student existStudent = studentRepository.findById(id).orElseThrow();
        // delete student from repository
        studentRepository.deleteById(existStudent.getStudentId());
        // check if student is in the repository, should return false
        boolean isStudentPresent = studentRepository.findById(id).isPresent();
        assertFalse(isStudentPresent);
    }
}