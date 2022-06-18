/**
 * @Author CHANTAL NIYONZIMA
 * 217267815
 * 14 June 2022
 * EmployeeAddressController.java
 */
package za.ac.cput.school_management_grp33.service.location.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.location.City;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeFactory;
import za.ac.cput.school_management_grp33.repository.location.CityRepository;
import za.ac.cput.school_management_grp33.service.employee.EmployeeService;
import za.ac.cput.school_management_grp33.service.employee.impl.EmployeeAddressServiceImpl;
import za.ac.cput.school_management_grp33.service.location.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;
    private final EmployeeAddressServiceImpl employeeAddressService;
    private final EmployeeService employeeService;


    @Autowired
    public CityServiceImpl(EmployeeService employeeService,CityRepository repository, EmployeeAddressServiceImpl employeeAddressService) {
        this.employeeAddressService = employeeAddressService;
        this.employeeService = employeeService;
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

    /**
     * THis method should return al the employees linked to a city.
     * 1) GetAll the Employee Address in a city !! List<EmployeeAddress> getEmployeeAddresses(City city) this method should come from EmployeeAddressService or EMployeeAddressRepository.
     * 2) Get The Employee Based on the staffId !! THis method need to be implemented on EmployeeService or EmployeeRepository.
     * @param cityId
     * @return
     */
    public List<Name> getEmployeesIn(String cityId){
        List<Name> names = new ArrayList<>();
    List<EmployeeAddress> employeeAddressList = employeeAddressService.getAllInCity(cityId);
        for(EmployeeAddress employeeAddress: employeeAddressList){
            Employee employee = employeeService.findById(employeeAddress.getStaffId()).orElse(null);
            if(employee!=null){
                    names.add(employee.getName());
            }
        }
        return names;
    }
}
