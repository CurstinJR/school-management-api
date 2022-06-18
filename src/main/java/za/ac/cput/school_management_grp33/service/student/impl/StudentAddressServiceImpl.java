package za.ac.cput.school_management_grp33.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.repository.student.StudentAddressRepository;
import za.ac.cput.school_management_grp33.service.student.StudentAddressService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentAddressServiceImpl implements StudentAddressService {

    private final StudentAddressRepository studentAddressRepository;

    @Autowired
    public StudentAddressServiceImpl(StudentAddressRepository studentAddressRepository) {
        this.studentAddressRepository = studentAddressRepository;
    }

    @Override
    public StudentAddress save(StudentAddress studentAddress) {
        return studentAddressRepository.save(studentAddress);
    }

    @Override
    public List<StudentAddress> findAll() {
        return studentAddressRepository.findAll();
    }

    @Override
    public Optional<StudentAddress> findById(String id) {
        return studentAddressRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        studentAddressRepository.deleteById(id);
    }
}
