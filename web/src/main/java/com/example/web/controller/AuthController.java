package com.example.web.controller;

import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import com.example.web.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<AuthenticationResponseDTO> registerUser
      (@Valid @RequestBody UserRegistrationRequestDTO registrationDTO) {

    return this.authService.registerUser(registrationDTO);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDTO> authenticate
      (@Valid @RequestBody AuthenticationRequestDTO authRequest) {

    return this.authService.authenticate(authRequest);
  }

  @GetMapping
  public ResponseEntity<?> helloMethod(){
    return ResponseEntity.ok("Hello");
  }
}
