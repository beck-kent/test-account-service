package kz.accounting.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import kz.accounting.commons.utils.Time;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiExceptionDto {

    /**
     * Current timestamp
     */
    @Builder.Default
    ZonedDateTime timestamp = Time.currentZonedDateTime();

    /**
     * HTTP status code
     */
    int status;

    /**
     * Reason phrase of HTTP status code
     */
    String error;

    /**
     * Error message
     */
    String message;

    /**
     * Request URI
     */
    String path;

}
