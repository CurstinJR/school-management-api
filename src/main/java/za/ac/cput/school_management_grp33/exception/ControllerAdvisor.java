/**
 * @Author Curstin Rose - 220275408
 * ControllerAdvisor.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.exception;

import com.sanctionco.jmail.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                errors,
                request.getDescription(false)
        );
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidEmailExceptionHandler(InvalidEmailException ex, WebRequest request) {
        return new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                ex.toString(),
                request.getDescription(false));
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorResponse emailExistsExceptionHandler(EmailExistsException ex, WebRequest request) {
        return new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
        return new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
