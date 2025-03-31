package src.danik.notificationservice.exception.handler;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse processEntityNotFoundException(EntityNotFoundException e) {
        log.error("Entity not found exception: {}", e.getMessage());
        return ErrorResponse.builder().message(e.getMessage()).status(404).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse processIllegalArgumentException(IllegalArgumentException e) {
        log.error("Bad request exception: {}", e.getMessage());
        return ErrorResponse.builder().message(e.getMessage()).status(400).build();
    }

    @ExceptionHandler(FeignException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse processFeignException(FeignException e) {
        log.error("Feign exception: {}", e.getMessage());
        return ErrorResponse.builder().message(e.getMessage()).status(503).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse processAllErrors(Exception e) {
        log.error("Other exception: {}", e.getMessage());
        return ErrorResponse.builder().message(e.getMessage()).status(500).build();
    }
}
