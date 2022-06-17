package za.ac.cput.school_management_grp33.service.location;

import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.service.IService;

import java.util.List;

public interface CityService extends IService<City,String> {
    List<Employee> getEmployeesLiving(String cityId);
}
