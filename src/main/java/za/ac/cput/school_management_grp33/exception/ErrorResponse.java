/**
 * @Author Curstin Rose - 220275408
 * ErrorResponse.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class ErrorResponse {

    private int statusCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private Object message;
    private String description;
}
