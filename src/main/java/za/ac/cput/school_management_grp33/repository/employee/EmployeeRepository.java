/**
 * @Author Curstin Rose - 220275408
 * EmployeeRepository.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.school_management_grp33.domain.employee.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findEmployeeByEmail(String email);

    boolean existsByEmail(String email);
}
