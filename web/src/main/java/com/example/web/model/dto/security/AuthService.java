package com.example.web.model.dto.security;

import com.example.web.model.enums.RoleType;
import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import com.example.web.model.responseDTO.UserRegistrationResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {

  UserRegistrationResponseDTO registerUser(
      UserRegistrationRequestDTO userRegistrationDTO, RoleType user);

  ResponseEntity<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO authRequest);
}
