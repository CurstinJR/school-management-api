package za.ac.cput.school_management_grp33.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.school_management_grp33.domain.location.Country;

public interface CountryRepository extends JpaRepository<Country,String> {

}
