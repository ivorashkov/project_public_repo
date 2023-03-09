package com.example.web.service;

import com.example.web.model.requestDto.UserRegistrationDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity<?> createUser(UserRegistrationDTO userRegistrationDTO);
}
