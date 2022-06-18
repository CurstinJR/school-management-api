/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 16 June 2022
 * EmployeeAddressService.java
 */
package za.ac.cput.school_management_grp33.service.employee;

import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.service.IService;

import java.util.Optional;

public interface EmployeeAddressService extends IService<EmployeeAddress, String> {

    Optional<EmployeeAddress> findEmployeeAddressByStaffId(String staffId);

    boolean existsByStaffId(String staffId);
}
