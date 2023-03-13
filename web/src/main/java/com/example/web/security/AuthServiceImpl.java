package com.example.web.security;

import com.example.web.constant.UserRegistrationConstants;
import com.example.web.exception.UserNotFoundException;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.enums.RoleType;
import com.example.web.model.requestDto.AuthenticationRequestDTO;
import com.example.web.model.requestDto.UserRegistrationRequestDTO;
import com.example.web.model.responseDTO.AuthenticationResponseDTO;
import com.example.web.model.responseDTO.UserRegistrationResponseDTO;
import com.example.web.repository.RoleTypeRepository;
import com.example.web.repository.UserRepository;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

  private final ValidatorUtil validatorUtil;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RoleTypeRepository roleTypeRepository;

  @Override
  public UserRegistrationResponseDTO registerUser
      (UserRegistrationRequestDTO registrationDTO, RoleType roleType) {
    log.info("[ INFO ] Loading method registerUser in AuthServiceImpl");

    //todo check what info this method is giving back
    try {
      if (this.userRepository.existsByEmail(registrationDTO.getEmail())) {
        log.error("[ ERROR ] While Loading AuthServiceImpl { registerUser } email already exists ");
        //email exists
        return null;
      }

      if (!registrationDTO.getPassword().equals(registrationDTO.getPasswordConfirm())) {
        log.error(
            "[ ERROR ] While Loading AuthServiceImpl { registerUser } passwords are not equal");
        //passwords are not equal
        return null;
      }

      var user = setNewUserFields(registrationDTO);

      switch (roleType) {
        case user ->  user.setRole(this.roleTypeRepository.findByRoleName(RoleType.user));
        case admin -> user.setRole(this.roleTypeRepository.findByRoleName(RoleType.admin));
        default -> user.setRole(this.roleTypeRepository.findByRoleName(RoleType.user));
      }
      userRepository.saveAndFlush(user);

      return this.validatorUtil.getDTOFromEntity(user, UserRegistrationResponseDTO.class);

    } catch (Exception e) {
      log.error("[ ERROR WHILE LOADING registerUser in AuthServiceImpl {}]", e.getMessage());
      return null;
    }

  }

  @Override
  public ResponseEntity<AuthenticationResponseDTO> authenticate(
      AuthenticationRequestDTO authRequest) {
    log.info("[ INFO ] Loading method authenticate in AuthServiceImpl");
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

    var jwtToken = this.jwtService.generateToken(new CustomUserDetails(user));
    AuthenticationResponseDTO response = AuthenticationResponseDTO.builder()
        .token(jwtToken)
        .build();
    return this.validatorUtil.responseEntity(response);
  }

  private UserEntity setNewUserFields(UserRegistrationRequestDTO registrationDTO) {
    log.info("[ INFO ] Loading method setNewUserFields in AuthServiceImpl");

    return UserEntity.builder()
        .firstName(registrationDTO.getFirstName())
        .lastName(registrationDTO.getLastName())
        .email(registrationDTO.getEmail())
        .password(passwordEncoder.encode(registrationDTO.getPassword()))
        .phoneNumber(registrationDTO.getPhoneNumber())
        .isActive(UserRegistrationConstants.NOT_ACTIVE)
        .approvedBy(UserRegistrationConstants.APPROVED_BY_INITIAL_ID)
        .hasStar(UserRegistrationConstants.HAS_NO_STAR)
        .build();
  }
}
