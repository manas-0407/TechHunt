package com.TechHunt.JOB_SERVICE.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandle(Exception e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}