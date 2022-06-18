/**
 * @Author Ngonidzaishe Erica Chipato
 * 218327315
 * 15 June 2022
 * StudentAddressFactory.java
 */
package za.ac.cput.school_management_grp33.factory.student;

import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.domain.student.StudentAddress;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;
import za.ac.cput.school_management_grp33.util.StringUtility;

public class StudentAddressFactory {
    public static StudentAddress build(String studentId, Address address) {
        StringUtility.checkStringParam("studentId", studentId);
        address = AddressFactory.build(address.getUnitNumber(),
                address.getComplexName(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getPostalCode(),
                address.getCity());
        return StudentAddress.builder()
                .studentId(studentId)
                .address(address)
                .build();
    }
}
