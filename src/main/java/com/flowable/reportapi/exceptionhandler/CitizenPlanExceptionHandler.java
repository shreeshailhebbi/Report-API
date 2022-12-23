package com.flowable.reportapi.exceptionhandler;

import com.flowable.reportapi.exception.CitizenPlanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CitizenPlanExceptionHandler {

    @ExceptionHandler(value = CitizenPlanException.class)
    public ResponseEntity<Object> exception(CitizenPlanException exception) {
        String message = exception.getMessage();
        Map<String, Object> response = new HashMap<>();
        response.put("status", false);
        response.put("code", HttpStatus.BAD_REQUEST);
        response.put("message", "It seems you're sending invalid request");
        response.put("errors", Collections.singletonList(message));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
