/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.controller.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.location.impl.CityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/cities/")
public class CityController {
    public static final String CITY_WITH_ID_NOT_FOUND_MSG = "City with id: %s not found";

    private final CityServiceImpl cityService;

    @Autowired
    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<?> createUpdate(@RequestBody City city) {
        boolean isExist = cityService.findById(city.getId()).isPresent();
        if (!isExist) {
            String idExistsMessage = String.format(CITY_WITH_ID_NOT_FOUND_MSG, city.getId());
            throw new ResourceNotFoundException(idExistsMessage);
        }
        City city1 = cityService.save(city);
        return new ResponseEntity<>(city1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable String id) {
        String notFoundMessage = String.format(CITY_WITH_ID_NOT_FOUND_MSG, id);
        City city = cityService.findById(id).orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(city);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!cityService.findById(id).isPresent()) {
            String notFoundMessage = String.format(CITY_WITH_ID_NOT_FOUND_MSG, id);
            throw new ResourceNotFoundException(notFoundMessage);
        }
        cityService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<City> readAll() {
        return cityService.findAll();
    }
}
