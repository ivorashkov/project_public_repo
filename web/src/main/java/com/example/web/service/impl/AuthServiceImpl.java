package com.example.web.service.impl;

import com.example.web.constant.UserRegistrationConstants;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.requestDto.UserRegistrationDTO;
import com.example.web.repository.UserRepository;
import com.example.web.service.AuthService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final ValidatorUtil validatorUtil;
  private final UserRepository userRepository;

  //login
  //logout

  //register
  @Override
  public ResponseEntity<?> createUser(UserRegistrationDTO registrationDTO) {

    if (this.userRepository.existsByEmail(registrationDTO.getEmail())) {
      //email exists
      return this.validatorUtil.responseEntityBoolean
          (!this.userRepository.existsByEmail(registrationDTO.getEmail()));
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String result = encoder.encode(registrationDTO.getPassword());

    if (!encoder.matches(registrationDTO.getPasswordConfirm(), result)) {
      //Password and ConfirmPassword doesnt match
      return ResponseEntity.badRequest().build();
    }

    return this.validatorUtil.responseEntity
        (this.userRepository.save(setNewUserFields(registrationDTO, result)));
  }

  private UserEntity setNewUserFields(UserRegistrationDTO registrationDTO, String result) {

    return new UserEntity(
        UserRegistrationConstants.NOT_ACTIVE,
        result,
        registrationDTO.getEmail(),
        registrationDTO.getFirstName(),
        registrationDTO.getLastName(),
        registrationDTO.getPhoneNumber(),
        UserRegistrationConstants.USER_ROLE,
        UserRegistrationConstants.APPROVED_BY_INITIAL_ID,
        UserRegistrationConstants.HAS_NO_STAR);
  }
}
