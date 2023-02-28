package com.example.web.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {


  @ExceptionHandler(TourOfferNotFoundException.class)
  public ResponseEntity<Object> handleTourOfferNotFoundException(
      TourOfferNotFoundException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Offer not found");

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

}
