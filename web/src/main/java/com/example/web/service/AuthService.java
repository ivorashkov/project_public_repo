package com.example.web.service;

import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  ResponseEntity<AuthenticationResponseDTO> registerUser(UserRegistrationRequestDTO userRegistrationDTO);

  ResponseEntity<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authRequest);
}
