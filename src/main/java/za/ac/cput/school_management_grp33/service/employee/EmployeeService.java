/**
 * @Author Curstin Rose - 220275408
 * EmployeeService.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.service.employee;

import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.service.IService;

import java.util.Optional;

public interface EmployeeService extends IService<Employee, String> {

    Optional<Employee> findEmployeeByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsById(String id);
}
