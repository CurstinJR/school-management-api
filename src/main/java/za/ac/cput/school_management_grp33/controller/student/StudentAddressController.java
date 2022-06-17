package za.ac.cput.school_management_grp33.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.exception.ResourceNotFoundException;
import za.ac.cput.school_management_grp33.service.student.impl.StudentAddressServiceImpl;

import javax.validation.Valid;
import java.util.List;

/*
Author: Kevin Lionel Mombo Ndinga (218180500)
StudentAddressController.java;
 */
@RestController
@RequestMapping("/api/students")
public class StudentAddressController {
    public static final String STUDENTADDRESS_WITH_ID_NOT_FOUND_MSG = "Student address with: %s id not found";
    public static final String STUDENTADDRESS_WITH_ADDRESS_NOT_FOUND_MSG = "Student address with: %s address not found";
    public static final String STUDENTADDRESS_ADDRESS_EXISTS_MSG = "Student address exists: %s";

    private final StudentAddressServiceImpl studentAddressService;

    @Autowired
    public StudentAddressController(StudentAddressServiceImpl studentAddressService) {
        this.studentAddressService = studentAddressService;
    }

    @GetMapping("/address")
    public ResponseEntity<List<StudentAddress>> getAllStudentAddress() {
        List<StudentAddress> studentAddresses = studentAddressService.findAll();
        return ResponseEntity.ok(studentAddresses);
    }

    @GetMapping("/{id}/address")
    public ResponseEntity<?> getStudentAddressById(@PathVariable String id) {
        String notFoundMessage = String.format(STUDENTADDRESS_WITH_ID_NOT_FOUND_MSG, id);
        StudentAddress studentAddress = studentAddressService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundMessage));
        return ResponseEntity.ok(studentAddress);

    }

    @PostMapping("/address")
    public ResponseEntity<?> addUpdateStudentAddress(@Valid @RequestBody StudentAddress studentAddress) {

        StudentAddress saveStudentAddress = studentAddressService.save(studentAddress);
        return new ResponseEntity<>(saveStudentAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/address")
    public ResponseEntity<?> deleteStudentAddressById(@PathVariable String id) {
        studentAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

