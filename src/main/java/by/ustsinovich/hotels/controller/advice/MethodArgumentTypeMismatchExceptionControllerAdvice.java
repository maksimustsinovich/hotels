package by.ustsinovich.hotels.controller.advice;

import by.ustsinovich.hotels.dto.ErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class MethodArgumentTypeMismatchExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorDto handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
    ) {
        String paramName = exception.getName();
        String providedValue = null;

        if (exception.getValue() != null) {
            providedValue = exception.getValue().toString();
        }

        Class<?> requiredType = exception.getRequiredType();
        if (requiredType != null && requiredType.getSimpleName().equals("HistogramParameter")) {
            return ErrorDto.builder()
                    .timestamp(System.currentTimeMillis())
                    .status(HttpStatus.BAD_REQUEST.value())
                    .error("Invalid Parameter")
                    .message(
                            ("Parameter '%s' has invalid value '%s'. " +
                             "Valid values are: brand, city, county, amenities").formatted(paramName, providedValue)
                    )
                    .path(request.getRequestURL().toString())
                    .build();
        }

        return ErrorDto.builder()
                .timestamp(System.currentTimeMillis())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Type Mismatch")
                .message("Failed to convert value '%s' to required type".formatted(providedValue))
                .path(request.getRequestURL().toString())
                .build();
    }

}