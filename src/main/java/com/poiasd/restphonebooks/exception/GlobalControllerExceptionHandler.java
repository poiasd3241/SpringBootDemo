package com.poiasd.restphonebooks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityRuntimeExceptions.EntityNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundExceptions(Exception ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityRuntimeExceptions.DuplicateEntityException.class)
    public final ResponseEntity<Object> handleDuplicateExceptions(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Validation error occurred for the object ");
        sb.append(ex.getObjectName());
        sb.append(" for the following field(s):");
        ex.getFieldErrors().forEach(fieldError -> sb.append("\n").append(fieldError.getField()));
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException() {
        String message = "Unknown error occurred";
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
