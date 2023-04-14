package com.example.jwt.controller;

import com.example.jwt.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidTopException(CustomException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }
}
