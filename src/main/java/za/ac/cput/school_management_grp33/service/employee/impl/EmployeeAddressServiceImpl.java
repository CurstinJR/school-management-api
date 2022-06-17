/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * EmployeeAddressServiceImpl.java
 */

package za.ac.cput.school_management_grp33.service.employee.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.factory.employee.EmployeeAddressFactory;
import za.ac.cput.school_management_grp33.repository.employee.EmployeeAddressRepository;
import za.ac.cput.school_management_grp33.service.employee.EmployeeAddressService;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

    private final EmployeeAddressRepository employeeAddressRepository;

    @Autowired
    public EmployeeAddressServiceImpl(EmployeeAddressRepository employeeAddressRepository) {
        this.employeeAddressRepository = employeeAddressRepository;
    }

    @Override
    public EmployeeAddress save(EmployeeAddress newEmployeeAddress) {
        String id = newEmployeeAddress.getStaffId();
        return findById(id).map(employeeAddress -> {
            String staffId = employeeAddress.getStaffId();
            Address address = newEmployeeAddress.getAddress();
            employeeAddress = EmployeeAddressFactory.build(staffId, address);
            return employeeAddressRepository.save(employeeAddress);

        }).orElseGet(() -> {
            String staffId = newEmployeeAddress.getStaffId();
            Address address = newEmployeeAddress.getAddress();

            return employeeAddressRepository.save(EmployeeAddressFactory.build(staffId, address));
        });
    }

    @Override
    public List<EmployeeAddress> findAll() {
        return employeeAddressRepository.findAll();
    }

    @Override
    public Optional<EmployeeAddress> findById(String s) {
        return Optional.empty();
    }


    /**
     * Find by identifier
     *
     * @param staffId String
     * @return Non-null Employee Address
     */

    @Override
    public Optional<EmployeeAddress> findEmployeeAddressByStaffId(String staffId) {
        return employeeAddressRepository.findEmployeeAddressByStaffId(staffId);
    }


    /**
     * Checks if employee address ID exists.
     *
     * @param staffId
     * @return boolean
     */

    @Override
    public boolean existsByStaffId(String staffId) {
        return employeeAddressRepository.existsById(staffId);
    }

    @Override
    public boolean existsByAddress(Address address) {
        return false;
    }


    /**
     * Deletes employee address from repository
     *
     * @param staffId
     * @return boolean
     */

    @Override
    public void deleteById(String staffId) {
        employeeAddressRepository.deleteById(staffId);
    }
}





