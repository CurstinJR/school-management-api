/**
 * @Author Curstin Rose - 220275408
 * EmployeeCityAPI.java
 * Created on 15 June 2022
 */
package za.ac.cput.school_management_grp33.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.employee.impl.EmployeeAddressServiceImpl;
import za.ac.cput.school_management_grp33.service.employee.impl.EmployeeServiceImpl;
import za.ac.cput.school_management_grp33.service.location.impl.CityServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeCityAPI {

    private final CityServiceImpl cityService;
    private final EmployeeAddressServiceImpl employeeAddressService;
    private final EmployeeServiceImpl employeeService;

    @Autowired
    public EmployeeCityAPI(CityServiceImpl cityService,
                           EmployeeAddressServiceImpl employeeAddressService,
                           EmployeeServiceImpl employeeService) {
        this.cityService = cityService;
        this.employeeAddressService = employeeAddressService;
        this.employeeService = employeeService;
    }

    /**
     * Question 6
     * THis method should return all the employees linked to a city.
     * 1) GetAll the Employee Address in a city !! List<EmployeeAddress> getEmployeeAddresses(City city)
     * this method should come from EmployeeAddressService or EmployeeAddressRepository.
     * 2) Get The Employee Based on the staffId !! THis method need to be implemented on
     * EmployeeService or EmployeeRepository.
     *
     * @param cityId String
     * @return List of Employee Name objects
     * @Author CHANTAL NIYONZIMA - 217267815
     */
    public List<Name> getEmployeesInCity(String cityId) {
        String errorMessage = "City Id: " + cityId + " not found";
        cityService.findById(cityId).orElseThrow(() -> new ResourceNotFoundException(errorMessage));
        List<Name> names = new ArrayList<>();
        List<EmployeeAddress> employeeAddressList = employeeAddressService.getAllInCity(cityId);
        for (EmployeeAddress employeeAddress : employeeAddressList) {
            Employee employee = employeeService.findById(employeeAddress.getStaffId()).orElse(null);
            if (employee != null) {
                names.add(employee.getName());
            }
        }
        return names;
    }
}
