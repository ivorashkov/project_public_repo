package com.example.web.security;

import com.example.web.model.enums.RoleType;
import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import com.example.web.util.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final ValidatorUtil validatorUtil;
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser
      (@Valid @RequestBody UserRegistrationRequestDTO registrationDTO) {

    return this.validatorUtil.responseEntity(
        this.authService.registerUser(registrationDTO, RoleType.user));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDTO> authenticate
      (@Valid @RequestBody AuthenticationRequestDTO authRequest) {

    return this.authService.authenticate(authRequest);
  }
}