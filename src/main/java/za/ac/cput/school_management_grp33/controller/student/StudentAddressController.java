package za.ac.cput.school_management_grp33.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.service.student.impl.StudentAddressServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentAddressController {

    private final StudentAddressServiceImpl studentAddressService;

    @Autowired
    public StudentAddressController(StudentAddressServiceImpl studentAddressService) {
        this.studentAddressService = studentAddressService;
    }

    @GetMapping("/address")
    public ResponseEntity<?> getAllStudentAddress() {
        List<StudentAddress> studentAddresses = studentAddressService.findAll();
        return ResponseEntity.ok(studentAddresses);
    }

}
