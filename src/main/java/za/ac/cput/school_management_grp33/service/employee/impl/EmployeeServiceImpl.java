/**
 * @Author Curstin Rose - 220275408
 * EmployeeServiceImpl.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.service.employee.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeRepository;
import za.ac.cput.school_management_grp33.service.employee.EmployeeService;
import za.ac.cput.school_management_grp33.util.Utils;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * The save() adds a new newEmployee to the repository and if the newEmployee
     * exists in the repository it will update the newEmployee's information.
     *
     * @param newEmployee Employee
     * @return Employee
     */
    @Override
    public Employee save(Employee newEmployee) {
        String id = newEmployee.getStaffId();
        return findById(id).map(employee -> {
            String staffId = employee.getStaffId();
            Name name = newEmployee.getName();
            String email = newEmployee.getEmail();
            employee = EmployeeFactory.build(staffId, email, name);
            return employeeRepository.save(employee);
        }).orElseGet(() -> {
            String staffId = newEmployee.getStaffId();
            String email = newEmployee.getEmail();
            Name name = newEmployee.getName();
            return employeeRepository.save(EmployeeFactory.build(staffId, email, name));
        });
    }

    /**
     * @return List of Employees
     */
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    /**
     * The findById() finds the employee by the identifier.
     *
     * @param id String
     * @return Non-null Employee
     */
    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    /**
     * The findEmployeeByEmail() finds the employee by the email address.
     *
     * @param email String
     * @return Non-null Employee
     */
    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        Utils.validateEmail(email);
        return employeeRepository.findEmployeeByEmail(email);
    }

    /**
     * Checks if Employee email is in the repository. It will return true if Employee email exists
     * otherwise false.
     *
     * @param email String
     * @return boolean
     */
    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    /**
     * Checks if Employee ID is in the repository. It will return true if Employee ID exists
     * otherwise false.
     *
     * @param id String
     * @return boolean
     */
    @Override
    public boolean existsById(String id) {
        return employeeRepository.existsById(id);
    }

    /**
     * To delete() removes the employee from the repository.
     *
     * @param id String
     */
    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }
}
