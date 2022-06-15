package za.ac.cput.school_management_grp33.util;

import com.sanctionco.jmail.EmailValidator;
import com.sanctionco.jmail.InvalidEmailException;
import com.sanctionco.jmail.JMail;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Utility Class, also known as Helper class.
 * It contains a bunch of related methods,
 * so they can be reused across the application.
 */
@UtilityClass
@Slf4j
public final class Utils {
    public static void validateEmail(String email) throws InvalidEmailException {
        if (JMail.isInvalid(email)) log.error("Invalid email.");
        EmailValidator emailValidator = JMail.strictValidator();
        emailValidator.enforceValid(email);
    }
}
