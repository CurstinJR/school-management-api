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

    /**
     * Using JMail to validate an email address. Enabling strict validator that restricts IP domain,
     * requires top level domain and restricts explicit source routing.
     *
     * @param email String
     * @throws InvalidEmailException Invalid email
     * @Author Curstin Rose - 220275408
     */
    public static void validateEmail(String email) throws InvalidEmailException {
        if (JMail.isInvalid(email)) log.error("Invalid email.");
        EmailValidator emailValidator = JMail.strictValidator();
        emailValidator.enforceValid(email);
    }

    public static int checkRangeNum(int value, int lowerBound, int upperBound) {
        if (!(value >= lowerBound && value <= upperBound)) {
            String error = String.format("Postal code %d is invalid. Please provide a postal code between %d and %d.",
                    value, lowerBound, upperBound);
            IllegalArgumentException exception = new IllegalArgumentException(error);
            log.info(exception.getMessage());
            throw exception;
        }
        return value;
    }
}
