/**
 * @Author Curstin Rose - 220275408
 * StudentServiceImplTest.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.service.student.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.student.StudentFactory;
import za.ac.cput.school_management_grp33.repository.student.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private Student student1;
    private Student student2;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        students = new ArrayList<>();
        student1 = StudentFactory.build("100", "joey13@email.com", new Name());
        student2 = StudentFactory.build("101", "jack14@email.com", new Name());
        students.add(student1);
        students.add(student2);
    }

    @AfterEach
    public void tearDown() {
        student1 = null;
        student2 = null;
        students = null;
    }

    @Test
    void save() {
        // given - stubbing - providing knowledge
        given(studentRepository.save(any(Student.class))).willReturn(student1);
        // when - action to be tested
        Student saveStudent = studentService.save(student1);
        // then - verify
        verify(studentRepository, times(1)).save(any(Student.class));
        assertNotNull(saveStudent);
    }

    @Test
    void findAll() {
        // given - stubbing - providing knowledge
        studentRepository.save(student2);
        given(studentRepository.findAll()).willReturn(students);
        // when - action to be tested
        List<Student> studentList = studentService.findAll();
        // then - verify
        assertAll(
                () -> assertNotNull(studentList),
                () -> assertTrue(studentList.size() >= 2),
                () -> assertEquals(studentList, students)
        );
        verify(studentRepository, times(1)).save(student2);
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given - stubbing - providing knowledge
        given(studentRepository.findById("100")).willReturn(Optional.of(student1));
        // when - action to be tested
        Student saveStudent = studentService.findById(student1.getStudentId()).orElseThrow();
        // then - verify
        assertNotNull(saveStudent);
        assertAll(
                () -> assertEquals("100", saveStudent.getStudentId()),
                () -> assertEquals("joey13@email.com", saveStudent.getEmail())
        );
    }

    @Test
    void findStudentByEmail() {
        // given - stubbing - providing knowledge
        String email = "jack14@email.com";
        given(studentRepository.findStudentByEmail(email)).willReturn(Optional.of(student2));
        // when - action to be tested
        Student existStudent = studentService.findStudentByEmail(student2.getEmail()).get();
        // then - verify
        assertNotNull(existStudent);
    }

    @Test
    void deleteById() {
        // given - stubbing - providing knowledge
        String id = "100";
        willDoNothing().given(studentRepository).deleteById(id);
        // when - action to be tested
        studentService.deleteById(student1.getStudentId());
        // then - verify
        verify(studentRepository, times(1)).deleteById(student1.getStudentId());
        assertFalse(studentRepository.existsById(id));
    }
}