/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 15 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.controller.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.employee.EmployeeAddressService;
import za.ac.cput.school_management_grp33.service.employee.impl.EmployeeAddressServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees/address")
public class EmployeeAddressController {

    public static final String EMPLOYEE_ADDRESS_WITH_ID_NOT_FOUND_MSG = "Employee with staffId: %s not found";
    private final EmployeeAddressService employeeAddressService;

    @Autowired
    public EmployeeAddressController(EmployeeAddressServiceImpl employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
    }

    /**
     * retrieves all employees addresses and return an array of
     * employee address objects.
     *
     * @return List of employee address objects
     */
    @GetMapping
    public ResponseEntity<List<EmployeeAddress>> getAllEmployeesAddress() {
        List<EmployeeAddress> employeesAddress = employeeAddressService.findAll();
        return ResponseEntity.ok(employeesAddress);
    }

    /**
     * retrieve a specific Employee Address  object by providing
     * an ID. Throws 404 NOT_FOUND, if Employee Address  object is not in the repository.
     *
     * @param staffId String
     * @return 200 and Employee Address object SUCCESS
     */

    @GetMapping("/{staffId}")
    public ResponseEntity<?> getEmployeeAddressById(@PathVariable String staffId) {
        String notFoundMessage = String.format(EMPLOYEE_ADDRESS_WITH_ID_NOT_FOUND_MSG, staffId);
        EmployeeAddress employeeAddress = employeeAddressService.findEmployeeAddressByStaffId(staffId)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(employeeAddress);
    }

    /**
     * Handles the request to create a new EmployeeAddress object or update an existing
     * EmployeeAddress object. Throws 400 BAD_REQUEST, if payload is malformed.
     * Throws 500 INTERNAL_SERVER_ERROR, if request can not be processed.
     *
     * @param employeeAddress Employee JSON payload
     * @return 201 and new Employee object
     */
    @PostMapping
    public ResponseEntity<?> addUpdateEmployeeAddress(@Valid @RequestBody EmployeeAddress employeeAddress) {
        EmployeeAddress saveEmployeeAddress = employeeAddressService.save(employeeAddress);
        return new ResponseEntity<>(saveEmployeeAddress, HttpStatus.CREATED);
    }

    /**
     * Handles the request to delete an employeeAddress object from the repository.
     * Throws 404 NOT_FOUND, if EmployeeAddress object is not in the repository.
     *
     * @param staffId String
     * @return 204 No content
     */
    @DeleteMapping("/{staffId}")
    public ResponseEntity<?> deleteEmployeeAddressById(@PathVariable String staffId) {
        if (!employeeAddressService.existsByStaffId(staffId)) {
            String notFoundMessage = String.format(EMPLOYEE_ADDRESS_WITH_ID_NOT_FOUND_MSG, staffId);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        employeeAddressService.deleteById(staffId);
        return ResponseEntity.noContent().build();
    }
}
