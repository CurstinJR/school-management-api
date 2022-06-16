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

    /**
     * Handles the request to retrieve all employees and return an array of
     * employee objects.
     *
     * @return List of employee objects
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    /**
     * Handles the request to retrieve a specific Employee object by providing
     * an ID. Throws 404 NOT_FOUND, if Employee object is not in the repository.
     *
     * @param id String
     * @return 200 and Employee object SUCCESS
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        String notFoundMessage = String.format(EMPLOYEE_WITH_ID_NOT_FOUND_MSG, id);
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(employee);
    }

    /**
     * Handles the request to create a new Employee object or update an existing
     * Employee object. Throws 400 BAD_REQUEST, if payload is malformed.
     * Throws 500 INTERNAL_SERVER_ERROR, if request can not be processed.
     *
     * @param employee Employee JSON payload
     * @return 201 and new Employee object
     */
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

    /**
     * Handles the request to delete an employee object from the repository.
     * Throws 404 NOT_FOUND, if Employee object is not in the repository.
     *
     * @param id String
     * @return 204 No content
     */
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
