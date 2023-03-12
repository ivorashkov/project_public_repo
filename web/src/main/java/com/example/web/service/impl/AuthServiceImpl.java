package com.example.web.service.impl;

import com.example.web.constant.UserRegistrationConstants;
import com.example.web.exception.UserNotFoundException;
import com.example.web.model.entity.RoleEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.enums.RoleType;
import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import com.example.web.repository.RoleTypeRepository;
import com.example.web.repository.UserRepository;
import com.example.web.service.AuthService;
import com.example.web.service.JwtService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final ValidatorUtil validatorUtil;
  private final UserRepository userRepository;
  private final RoleTypeRepository roleTypeRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Override
  public ResponseEntity<AuthenticationResponseDTO> registerUser
      (UserRegistrationRequestDTO registrationDTO) {

    //todo check what info this method is giving back
    if (this.userRepository.existsByEmail(registrationDTO.getEmail())) {
      //email exists
      return this.validatorUtil.responseEntityBoolean
          (!this.userRepository.existsByEmail(registrationDTO.getEmail()));
    }
//
//    String result = this.passwordEncoder.encode(registrationDTO.getPassword());
//    if (!this.passwordEncoder.matches(registrationDTO.getPasswordConfirm(), result)) {
//      //Password and ConfirmPassword doesn't match
//      return ResponseEntity.badRequest().build();
//    }

    var user = setNewUserFields(registrationDTO);
    userRepository.saveAndFlush(user);

    var jwtToken = this.jwtService.generateToken(user);

    return this.validatorUtil.responseEntity(
        AuthenticationResponseDTO.builder()
            .token(jwtToken)
            .build());
  }

  @Override
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      AuthenticationRequestDTO authRequest) {

    //todo check what info this method is giving back
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            authRequest.getEmail(),
            authRequest.getPassword()
        )
    );

    //user is correct at this point
    var user = this.userRepository.findUserEntityByEmail(authRequest.getEmail())
        .orElseThrow(UserNotFoundException::new);

    var jwtToken = this.jwtService.generateToken(user);
    return this.validatorUtil.responseEntity(
        AuthenticationResponseDTO.builder()
            .token(jwtToken)
            .build());
  }

  private UserEntity setNewUserFields(UserRegistrationRequestDTO registrationDTO) {
    var role = RoleEntity.builder()
        .roleName(RoleType.user)
        .build();

    return UserEntity.builder()
        .firstName(registrationDTO.getFirstName())
        .lastName(registrationDTO.getLastName())
        .email(registrationDTO.getEmail())
        .password(passwordEncoder.encode(registrationDTO.getPassword()))
        .phoneNumber(registrationDTO.getPhoneNumber())
        .isActive(UserRegistrationConstants.NOT_ACTIVE)
        .approvedBy(UserRegistrationConstants.APPROVED_BY_INITIAL_ID)
        .hasStar(UserRegistrationConstants.HAS_NO_STAR)
        .role(this.roleTypeRepository.saveAndFlush(role))
        .build();
  }
}
