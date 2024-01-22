package org.example.taskmanagement.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<CustomException> handleThereIsNoSuchDepartmentException() {
        return new ResponseEntity<>(new CustomException("Object is not exist"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class CustomException {
        private String message;
    }
}
