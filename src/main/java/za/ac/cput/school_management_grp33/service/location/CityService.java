/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.service.location;

import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.service.IService;

import java.util.List;

public interface CityService extends IService<City, String> {
    //Question 7: Tarren-Marc Adams
    List<City> findCitiesByCountry_IdOrderByName(String id);
}
