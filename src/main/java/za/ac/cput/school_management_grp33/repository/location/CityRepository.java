/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.school_management_grp33.domain.location.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    //Question 7: Tarren-Marc Adams
    List<City> findCitiesByCountry_IdOrderByName(String id);
}
