/**
 * @Author Curstin Rose - 220275408
 * EmployeeController.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.exception.EmailExistsException;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.employee.impl.EmployeeServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    public static final String EMPLOYEE_WITH_ID_NOT_FOUND_MSG = "Employee with id: %s not found";
    public static final String EMPLOYEE_EMAIL_EXISTS_MSG = "Employee email exists: %s";
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        String notFoundMessage = String.format(EMPLOYEE_WITH_ID_NOT_FOUND_MSG, id);
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<?> addUpdateEmployee(@Valid @RequestBody Employee employee) {
        boolean existsByEmail = employeeService.existsByEmail(employee.getEmail());
        if (existsByEmail) {
            String emailExistsMessage = String.format(EMPLOYEE_EMAIL_EXISTS_MSG, employee.getEmail());
            throw new EmailExistsException(emailExistsMessage);
        }
        Employee saveEmployee = employeeService.save(employee);
        return new ResponseEntity<>(saveEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id) {
        if (!employeeService.existsById(id)) {
            String notFoundMessage = String.format(EMPLOYEE_WITH_ID_NOT_FOUND_MSG, id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
