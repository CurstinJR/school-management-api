/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.service.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.repository.location.CityRepository;
import za.ac.cput.school_management_grp33.service.location.CityService;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public City save(City city) {
        return repository.save(city);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<City> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    //Question7: Tarren-Marc Adams
    @Override
    public List<City> findCitiesByCountry_IdOrderByName(String id) {
        return repository.findCitiesByCountry_IdOrderByName(id);
    }
}
