package dev.abykov.pets.edusphere.courses.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

/**
 * Centralized handler for application exceptions.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<Object> handleCourseNotFound(CourseNotFoundException ex) {
        LOG.warn(">>> handleCourseNotFound called: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Course not found", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtExceptions(Exception ex) {
        LOG.error(">>> handleAllUncaughtExceptions called", ex);

        if (ex.getCause() instanceof CourseNotFoundException courseNotFound) {
            return handleCourseNotFound(courseNotFound);
        }

        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", ex.getMessage());
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message) {
        return new ResponseEntity<>(
                new ErrorResponse(status.value(), error, message, ZonedDateTime.now()),
                status
        );
    }

    private static class ErrorResponse {
        private final int status;
        private final String error;
        private final String message;
        private final ZonedDateTime timestamp;

        public ErrorResponse(int status, String error, String message, ZonedDateTime timestamp) {
            this.status = status;
            this.error = error;
            this.message = message;
            this.timestamp = timestamp;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }

        public ZonedDateTime getTimestamp() {
            return timestamp;
        }
    }
}
