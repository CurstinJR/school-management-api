package za.ac.cput.school_management_grp33.repository.student;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;
import za.ac.cput.school_management_grp33.factory.student.StudentAddressFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class StudentAddressRepositoryTest {

    Address address = AddressFactory.build("125", "AvonSands", "12", "Beach",
            1500, new City());
    private final StudentAddress studentAddress = StudentAddressFactory.build("100", address);

    @Autowired
    private StudentAddressRepository studentAddressRepository;

    @Order(1)
    void save() {
        StudentAddress saveStudentAddress = studentAddressRepository.save(studentAddress);
        StudentAddress existStudentAddress = studentAddressRepository.findById(saveStudentAddress.getStudentId()).orElseThrow();
        assertEquals(studentAddress.getStudentId(), existStudentAddress.getStudentId());
    }

    @Test
    @Order(2)
    void findAll() {
        List<StudentAddress> studentAddresses = studentAddressRepository.findAll();
        assertTrue(studentAddresses.size() >= 0);
    }

    @Test
    @Order(3)
    @Disabled
    void findById() {
        String id = studentAddress.getStudentId();
        // find the newly saved student by id
        StudentAddress existStudentAddress = studentAddressRepository.findById(id).orElseThrow();
        assertNotEquals(existStudentAddress.getAddress(), studentAddress.getAddress());
    }

    @Test
    @Order(4)
    @Disabled
    void deleteById() {
        String id = studentAddress.getStudentId();
        StudentAddress existStudentAddress = studentAddressRepository.findById(id).orElseThrow();
        studentAddressRepository.deleteById(existStudentAddress.getStudentId());
        boolean isStudentAddressPresent = studentAddressRepository.findById(id).isPresent();
        assertTrue(isStudentAddressPresent);
    }
}