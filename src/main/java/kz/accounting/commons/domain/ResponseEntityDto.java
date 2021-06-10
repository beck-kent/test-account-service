package kz.accounting.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.accounting.commons.utils.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseEntityDto<T> {

    /**
     * Current timestamp
     */
    @Builder.Default
    ZonedDateTime timestamp = Time.currentZonedDateTime();

    /**
     * Response state
     */
    @Builder.Default
    private boolean success = Boolean.TRUE;

    /**
     * Error message
     */
    String error;

    /**
     * Code of business error
     */
    @JsonProperty("error_code")
    String errorCode;

    /**
     * Response message
     */
    String message;

    /**
     * Response body
     */
    T data;

    public static <T> ResponseEntityDto<T> ok(T body) {
        return ResponseEntityDto.<T> builder().data(body).build();
    }

    public static ResponseEntityDto<?> ok() {
        return ResponseEntityDto.builder().build();
    }

    public static ResponseEntityDto<?> error(String message) {
        return ResponseEntityDto.builder()
                .success(false)
                .error(message)
                .build();
    }

}