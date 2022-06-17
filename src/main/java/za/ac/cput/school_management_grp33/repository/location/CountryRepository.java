package za.ac.cput.school_management_grp33.repository.location;

/*
CountryRepository.java
Author: Tarren-Marc Adams - 214041794
Date: 16 March 2022
 */

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.school_management_grp33.domain.location.Country;

public interface CountryRepository extends JpaRepository<Country,String> {
}
