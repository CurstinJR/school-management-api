/**
 * @Author Curstin Rose - 220275408
 * EmployeeFactory.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.factory.employee;

import za.ac.cput.school_management_grp33.domain.employee.Employee;
import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;
import za.ac.cput.school_management_grp33.util.StringUtility;
import za.ac.cput.school_management_grp33.util.Utils;

public class EmployeeFactory {
    public static Employee build(String staffId, String email, Name name) {
        StringUtility.checkStringParam("staffId", staffId);
        StringUtility.checkStringParam("email", email);
        Utils.validateEmail(email);
        name = NameFactory.build(name.getFirstName(), name.getMiddleName(), name.getLastName());
        return Employee.builder()
                .staffId(staffId)
                .email(email)
                .name(name)
                .build();
    }
}
