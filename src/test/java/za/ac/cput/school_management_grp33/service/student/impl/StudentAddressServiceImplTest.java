package za.ac.cput.school_management_grp33.service.student.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;
import za.ac.cput.school_management_grp33.factory.student.StudentAddressFactory;
import za.ac.cput.school_management_grp33.repository.student.StudentAddressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentAddressServiceImplTest {

    @Mock
    private StudentAddressRepository studentAddressRepository;

    @InjectMocks
    private StudentAddressServiceImpl studentAddressService;

    private StudentAddress studentAddress1;
    private StudentAddress studentAddress2;
    private List<StudentAddress> studentAddresses;

    @BeforeEach
    void setUp() {
        Address address = AddressFactory.build("125", "AvonSands", "12", "Beach",
                1500, new City());
        studentAddresses = new ArrayList<>();
        studentAddress1 = StudentAddressFactory.build("100", address);
        studentAddress2 = StudentAddressFactory.build("101", address);
        studentAddresses.add(studentAddress1);
        studentAddresses.add(studentAddress2);
    }

    @AfterEach
    public void tearDown() {
        studentAddress1 = null;
        studentAddress2 = null;
        studentAddresses = null;
    }

    @Test
    void findAll() {
        // given - stubbing - providing knowledge
        studentAddressRepository.save(studentAddress2);
        given(studentAddressRepository.findAll()).willReturn(studentAddresses);
        // when - action to be tested
        List<StudentAddress> studentAddressesList = studentAddressService.findAll();
        // then - verify
        assertAll(
                () -> assertNotNull(studentAddressesList),
                () -> assertTrue(studentAddressesList.size() >= 2),
                () -> assertEquals(studentAddressesList, studentAddresses)
        );
        verify(studentAddressRepository, times(1)).save(studentAddress2);
        verify(studentAddressRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given - stubbing - providing knowledge
        given(studentAddressRepository.findById("100")).willReturn(Optional.of(studentAddress1));
        // when - action to be tested
        StudentAddress saveStudentAddress = studentAddressService.findById(studentAddress1.getStudentId()).orElseThrow();
        // then - verify
        assertNotNull(saveStudentAddress);
        assertAll(
                () -> assertEquals("100", saveStudentAddress.getStudentId()),
                () -> assertEquals("Beach", saveStudentAddress.getAddress().getStreetName())
        );
    }

}