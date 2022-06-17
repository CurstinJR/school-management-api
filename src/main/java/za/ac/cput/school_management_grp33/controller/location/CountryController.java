package za.ac.cput.school_management_grp33.controller.location;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.location.impl.CountryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    public static final String COUNTRY_WITH_ID_NOT_FOUND_MSG =  "Country with id: %s not found";
    private final CountryServiceImpl service;

    @Autowired
    public CountryController(CountryServiceImpl service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries(){
        List<Country> countries = service.findAll();
        return ResponseEntity.ok(countries);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCountryId(@PathVariable String id){
        String notFound = String.format(COUNTRY_WITH_ID_NOT_FOUND_MSG, id);
        Country country = service.findById(id).orElseThrow(() -> new ResourceNotFoundException(notFound));
        return ResponseEntity.ok(country);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountryId(@PathVariable String id){
        if (service.findById(id).isEmpty()) {
            String notFoundMessage = String.format(COUNTRY_WITH_ID_NOT_FOUND_MSG, id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
