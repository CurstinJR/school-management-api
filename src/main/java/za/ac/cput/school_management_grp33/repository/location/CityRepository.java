package za.ac.cput.school_management_grp33.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.school_management_grp33.domain.location.City;

@Repository
public interface CityRepository extends JpaRepository<City,String> {
}
