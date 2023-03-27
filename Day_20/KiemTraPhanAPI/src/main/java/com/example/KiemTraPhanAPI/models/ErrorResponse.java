package com.example.KiemTraPhanAPI.models;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private HttpStatus httpStatus;
    private Object message;
}
