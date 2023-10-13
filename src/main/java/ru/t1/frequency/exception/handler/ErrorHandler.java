package ru.t1.frequency.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.t1.frequency.exception.BadRequestException;
import ru.t1.frequency.exception.NotFoundException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(final RuntimeException e) {
        return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final RuntimeException e) {
        return new ErrorResponse(e.getClass().getSimpleName(), e.getMessage());
    }

    public static class ErrorResponse {
        private final String errorName;
        private final String errorMessage;

        public ErrorResponse(String errorName, String errorMessage) {
            this.errorName = errorName;
            this.errorMessage = errorMessage;
        }

        public String getErrorName() {
            return errorName;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
