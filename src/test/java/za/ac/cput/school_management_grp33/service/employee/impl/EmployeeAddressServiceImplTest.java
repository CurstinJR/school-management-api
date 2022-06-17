/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 17 June 2022
 * EmployeeAddressServiceImplTest.java
 */

package za.ac.cput.school_management_grp33.service.employee.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeAddressFactory;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeAddressRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@ExtendWith(MockitoExtension.class)



class EmployeeAddressServiceImplTest {

    @Mock
    private EmployeeAddressRepository employeeAddressRepository;

    @InjectMocks
    private EmployeeAddressServiceImpl employeeAddressService;

    private Address address1, address2;

    private EmployeeAddress employeeAddress1, employeeAddress2;
    private List<EmployeeAddress> employeeAddresses;


    @BeforeEach
    void setup(){
        employeeAddresses = new ArrayList<>();
        address1 = AddressFactory.build("001","Uistig","375",
                "87","portfield",1744,new City());
        address2 = AddressFactory.build("005","Boston Heights","554",
                "35","Yardley-ville",7949,new City());
        

        employeeAddress1 = EmployeeAddressFactory.build("1", address1);
        employeeAddress2 = EmployeeAddressFactory.build("1",address2 );
        
        employeeAddresses.add(employeeAddress1);
        employeeAddresses.add(employeeAddress2);
       
    }

    @AfterEach
    void teardown(){
        employeeAddress1 = null;
        employeeAddress2 = null;
        employeeAddresses = null;
    }

    @Test
    void save(){
        given(employeeAddressRepository.save(any(EmployeeAddress.class))).willReturn(employeeAddress1);
       EmployeeAddress saveEmployeeAddress = employeeAddressService.save(employeeAddress1);
        verify(employeeAddressRepository, times(1)).save(any(EmployeeAddress.class));
        assertNotNull(saveEmployeeAddress);
    }

    @Test
    void findAll(){
        employeeAddressRepository.save(employeeAddress2);
        given(employeeAddressRepository.findAll()).willReturn(employeeAddresses);
        List<EmployeeAddress> employeeAddressList = employeeAddressService.findAll();
        assertAll(
                () -> assertNotNull(employeeAddressList),
                () -> assertTrue(employeeAddressList.size() >= 2),
                () -> assertEquals(employeeAddressList,employeeAddresses)
        );
    }

    @Test
    void findById() {
        given(employeeAddressRepository.findById("25")).willReturn(Optional.of(employeeAddress2));
        
        EmployeeAddress saveemployeeAddress = employeeAddressService.findById(employeeAddress2.getStaffId()).orElseThrow();
        assertNotNull(saveemployeeAddress);
        assertAll(
                () -> assertEquals("25", saveemployeeAddress.getStaffId())
        );
    }
    @Test
    void deleteById() {
        String id = "80";
        employeeAddressService.deleteById(employeeAddress2.getStaffId());
        assertFalse(employeeAddressRepository.existsById(id));
    }


}