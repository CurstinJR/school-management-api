package za.ac.cput.school_management_grp33.controller.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.school_management_grp33.domain.location.Country;
import za.ac.cput.school_management_grp33.service.location.impl.CountryServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryServiceImpl countryService;

    @Autowired
    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCountries() {
        List<Country> countries = countryService.findAll();
        return ResponseEntity.ok(countries);
    }
}
