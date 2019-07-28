package com.project.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {


  /**
   * Global Exception handler for all exceptions.
   */
  @ExceptionHandler
  public ResponseEntity<Map<String, Object>> handle(Exception exception) {

    HashMap<String,Object> response = new HashMap<>();
    response.put("status", false);
    response.put("message", exception.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
