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


  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<Object> handleUserNotFoundException(
      UserNotFoundException ex, WebRequest request) {

    Map<String, Object> body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "User not found");

    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MapEntityPageIntoDtoPageException.class)
  public ResponseEntity<Object> handleMapEntityPageIntoDTOPageException
      (MapEntityPageIntoDtoPageException ex, WebRequest request){

    Map<String,Object>  body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Error while trying to Map Page<Offer> into Page<DTO>");

    return new ResponseEntity<>(body, HttpStatus.FOUND);
  }

  @ExceptionHandler(PageWithOffersNotFoundException.class)
  public ResponseEntity<Object> handlePageWithOffersNotFoundException
      (PageWithOffersNotFoundException ex, WebRequest request){

    Map<String,Object>  body = new LinkedHashMap<>();
    body.put("timestamp", LocalDateTime.now());
    body.put("message", "Page not found");

    return new ResponseEntity<>(body, HttpStatus.FOUND);
  }


}
