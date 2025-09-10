package com.example.demo.advice;

import com.example.demo.exception.InvalidEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException exceptionHandler(Exception exception) {
        return new ResponseException(exception.getMessage());
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseException exceptionHandlerEmployee(Exception exception) {
        return new ResponseException(exception.getMessage());
    }
}
