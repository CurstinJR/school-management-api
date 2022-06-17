package za.ac.cput.school_management_grp33.exception;

public class AddressExistsException extends RuntimeException {
    public AddressExistsException() {
    }

    public AddressExistsException(String message) {
        super(message);
    }

    public AddressExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressExistsException(Throwable cause) {
        super(cause);
    }

    public AddressExistsException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
