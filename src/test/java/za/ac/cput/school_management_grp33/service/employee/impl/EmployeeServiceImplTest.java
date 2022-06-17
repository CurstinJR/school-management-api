/**
 * @Author Curstin Rose - 220275408
 * EmployeeServiceImplTest.java
 * Created on 15 June 2022
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
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeFactory;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Name name1;
    private Name name2;
    private Employee employee1;
    private Employee employee2;
    private List<Employee> employees;

    @BeforeEach
    void setUp() {
        employees = new ArrayList<>();
        name1 = NameFactory.build("JoeSo", "Thirteen", "Roy");
        name2 = NameFactory.build("JackGo", "Fourteen", "Soy");
        employee1 = EmployeeFactory.build("200", "joeySo13@email.com", name1);
        employee2 = EmployeeFactory.build("201", "jackGo14@email.com", name2);
        employees.add(employee1);
        employees.add(employee2);
    }

    @AfterEach
    void tearDown() {
        employee1 = null;
        employee2 = null;
        employees = null;
    }

    @Test
    void save() {
        // given - stubbing - providing knowledge
        given(employeeRepository.save(any(Employee.class))).willReturn(employee1);
        // when - action to be tested
        Employee saveEmployee = employeeService.save(employee1);
        // then - verify
        verify(employeeRepository, times(1)).save(any(Employee.class));
        assertNotNull(saveEmployee);
    }

    @Test
    void findAll() {
        // given - stubbing - providing knowledge
        employeeRepository.save(employee2);
        given(employeeRepository.findAll()).willReturn(employees);
        // when - action to be tested
        List<Employee> employeeList = employeeService.findAll();
        // then - verify
        assertAll(
                () -> assertNotNull(employeeList),
                () -> assertTrue(employeeList.size() >= 2),
                () -> assertEquals(employeeList, employees)
        );
        verify(employeeRepository, times(1)).save(employee2);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        // given - stubbing - providing knowledge
        given(employeeRepository.findById("200")).willReturn(Optional.of(employee1));
        // when - action to be tested
        Employee saveEmployee = employeeService.findById(employee1.getStaffId()).orElseThrow();
        // then - verify
        assertNotNull(saveEmployee);
        assertAll(
                () -> assertEquals("200", saveEmployee.getStaffId()),
                () -> assertEquals("joeySo13@email.com", saveEmployee.getEmail())
        );
    }

    @Test
    void findEmployeeByEmail() {
        // given - stubbing - providing knowledge
        String email = "jackGo14@email.com";
        given(employeeRepository.findEmployeeByEmail(email)).willReturn(Optional.of(employee2));
        // when - action to be tested
        Employee existEmployee = employeeService.findEmployeeByEmail(employee2.getEmail()).get();
        // then - verify
        assertNotNull(existEmployee);
    }

    @Test
    void deleteById() {
        // given - stubbing - providing knowledge
        String id = "200";
        willDoNothing().given(employeeRepository).deleteById(id);
        // when - action to be tested
        employeeService.deleteById(employee1.getStaffId());
        // then - verify
        verify(employeeRepository, times(1)).deleteById(employee1.getStaffId());
        assertFalse(employeeRepository.existsById(id));
    }
}