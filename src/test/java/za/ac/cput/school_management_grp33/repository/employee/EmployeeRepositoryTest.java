/**
 * @Author Curstin Rose - 220275408
 * EmployeeRepositoryTest.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.repository.employee;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeFactory;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class EmployeeRepositoryTest {

    private final Name name = NameFactory.build("Joeless", "Thirteen", "Roy");
    private final Employee employee = EmployeeFactory.build("100", "joe13@email.com", name);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    void save() {
        Employee saveEmployee = employeeRepository.save(employee);
        Employee existEmployee = employeeRepository.findById(saveEmployee.getStaffId()).orElseThrow();
        assertEquals(employee.getStaffId(), existEmployee.getStaffId());
    }

    @Test
    @Order(2)
    void findAll() {
        // there are 3 employees in the database
        // get all employees from repository including the newly saved employee
        List<Employee> employees = employeeRepository.findAll();
        assertTrue(employees.size() >= 4);
    }

    @Test
    @Order(3)
    void findById() {
        String id = employee.getStaffId();
        // find the newly saved employee by id
        Employee existEmployee = employeeRepository.findById(id).orElseThrow();
        assertEquals(existEmployee.getEmail(), employee.getEmail());
    }

    @Test
    @Order(5)
    void deleteById() {
        String id = employee.getStaffId();
        Employee existEmployee = employeeRepository.findById(id).orElseThrow();
        // delete employee from repository
        employeeRepository.deleteById(existEmployee.getStaffId());
        // check if employee is in the repository, should return false
        boolean isEmployeePresent = employeeRepository.findById(id).isPresent();
        assertFalse(isEmployeePresent);
    }
}