package za.ac.cput.school_management_grp33.service.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.repository.location.CityRepository;
import za.ac.cput.school_management_grp33.service.location.CityService;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private static  CityServiceImpl cityService = null;
    @Autowired
    private CityRepository repository;

    private CityServiceImpl(){

    }
    public static CityServiceImpl getCityService() {
        if(cityService == null){
            cityService = new CityServiceImpl();
        }
        return cityService;
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
    public Optional<City> findById(String s) {
        return repository.findById(s);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }

    @Override
    public List<Employee> getEmployeesLiving(String cityId) {
        return null;
    }
}
