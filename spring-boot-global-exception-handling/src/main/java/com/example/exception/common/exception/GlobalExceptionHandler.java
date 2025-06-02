package com.example.exception.common.exception;

import com.example.exception.common.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

/**
 * GlobalExceptionHandler is a centralized exception handler for the application.
 * It handles specific exceptions and returns appropriate HTTP responses with
 * localized error messages.
 *
 * <p>Dependencies:</p>
 * <ul>
 *   <li>{@code @Value("${server.error.path}") String errorPath}: This value is injected
 *   from the application properties and represents the error path to be included in the error response.</li>
 *   <li>{@link I18nService}: This service is used to retrieve localized messages.</li>
 * </ul>
 *
 * <p>Annotations:</p>
 * <ul>
 *   <li>{@code @ControllerAdvice}: Marks this class as a global exception handler.</li>
 *   <li>{@code @RequiredArgsConstructor}: Generates a constructor with required arguments.</li>
 * </ul>
 *
 * @since 0.0.1
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Setter
    @Value("${server.error.path}")
    private String errorPath;

    private final I18nService i18nService;

//    @ExceptionHandler(ResourceNotFoundException.class)
//    protected ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return getProblemDetail(
//                HttpStatus.NOT_FOUND,
//                ex.getMessage()
//        );
//    }

    /**
     * Handles ResourceNotFoundException and returns a ProblemDetail object
     * with HTTP status 404 (Not Found) and a localized error message.
     *
     * @param ex the ResourceNotFoundException thrown when a resource is not found
     * @return ProblemDetail object containing the error details and HTTP status
     */
    @ExceptionHandler(ResourceNotFoundExceptionWithI18n.class)
    protected ProblemDetail handleResourceNotFoundExceptionWithI18n(ResourceNotFoundExceptionWithI18n ex) {
        return getProblemDetail(
                HttpStatus.NOT_FOUND,
                i18nService.getMessage(
                        ex.getMessage(),
                        ex.getArgs()
                )
        );
    }

    /**
     * Constructs a ProblemDetail object with the specified HTTP status and detail message.
     * Sets the type of the problem detail to the configured error path.
     *
     * @param httpStatus the HTTP status to set for the ProblemDetail
     * @param detail the detail message to set for the ProblemDetail
     * @return ProblemDetail object with the specified status and detail message
     */
    private ProblemDetail getProblemDetail(HttpStatus httpStatus, String detail) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                httpStatus,
                detail
        );
        pd.setType(URI.create(errorPath));
        return pd;
    }
}