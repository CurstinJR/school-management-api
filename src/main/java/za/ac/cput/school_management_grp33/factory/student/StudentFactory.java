/**
 * @Author Curstin Rose - 220275408
 * StudentFactory.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.factory.student;

import za.ac.cput.school_management_grp33.domain.lookup.Name;
import za.ac.cput.school_management_grp33.domain.student.Student;
import za.ac.cput.school_management_grp33.factory.lookup.NameFactory;
import za.ac.cput.school_management_grp33.util.StringUtility;
import za.ac.cput.school_management_grp33.util.Utils;

public class StudentFactory {

    public static Student build(String studentId, String email, Name name) {
        StringUtility.checkStringParam("studentId", studentId);
        StringUtility.checkStringParam("email", email);
        Utils.validateEmail(email);
        name = NameFactory.build(name.getFirstName(), name.getMiddleName(), name.getLastName());
        return Student.builder()
                .studentId(studentId)
                .email(email)
                .name(name)
                .build();
    }
}
