package za.ac.cput.school_management_grp33.factory.student;

import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.util.StringUtility;

public class StudentAddressFactory {
    public static StudentAddress build(String studentId, Address address) {
        StringUtility.checkStringParam("studentId", studentId);
        return StudentAddress.builder()
                .studentId(studentId)
                .address(address)
                .build();
    }
}
