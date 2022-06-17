/**
        * @Author Ngonidzaishe Erica Chipato
        * 218327315
        * 15 June 2022
        * EmployeeAddressFactory.java
        */

package za.ac.cput.school_management_grp33.factory.employee;


import za.ac.cput.school_management_grp33.domain.employee.EmployeeAddress;
import za.ac.cput.school_management_grp33.domain.lookup.Address;
import za.ac.cput.school_management_grp33.factory.lookup.AddressFactory;
import za.ac.cput.school_management_grp33.util.StringUtility;

public class EmployeeAddressFactory
{
    public static EmployeeAddress build(String staffId, Address address)
    {
        StringUtility.checkStringParam("staffId", staffId);
        address = AddressFactory.build( address.getUnitNumber(),address.getComplexName(),address.getComplexNumber(),
                address.getStreetNumber(),
                address.getStreetName(), address.getPostalCode(),
                address.getCity());

        return EmployeeAddress.builder()
                .staffId(staffId)
                .address(address)
                .build();
    }
}
