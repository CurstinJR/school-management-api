/**
 * @Author Curstin Rose - 220275408
 * StudentCountryAPI.java
 * Created on 15 June 2022
 */
package za.ac.cput.school_management_grp33.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.cput.school_management_grp33.service.student.StudentAddressService;
import za.ac.cput.school_management_grp33.service.student.StudentService;

import java.util.List;

@Component
public class StudentCountryAPI {

    private final StudentService studentService;
    private final StudentAddressService studentAddressService;

    @Autowired
    public StudentCountryAPI(StudentService studentService, StudentAddressService studentAddressService) {
        this.studentService = studentService;
        this.studentAddressService = studentAddressService;
    }

    /**
     * Question 9
     *
     * @param countryId String
     * @return List of Student Last Names
     */
    public List<String> findStudentsInCountry(String countryId) {
        return studentAddressService.findAll().stream()
                .filter(studentAddress -> studentAddress
                        .getAddress()
                        .getCity()
                        .getCountry()
                        .getId()
                        .equalsIgnoreCase(countryId))
                .map(studentAddress -> studentService
                        .findById(studentAddress.getStudentId()).get().getName().getLastName())
                .toList();
    }
}
