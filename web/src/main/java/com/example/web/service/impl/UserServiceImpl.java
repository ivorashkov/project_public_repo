package com.example.web.service.impl;

import com.example.web.exception.UserNotFoundException;
import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import com.example.web.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ValidatorUtil validatorUtil;

  @Override
  public UserDTO findUserDTOById(Long id) {
    log.info(" [INFO] Loading UserServiceImpl {findUserDTOById} ");
    UserEntity userEntity = null;
    try {
      userEntity =
          this.userRepository.findUserEntityById(id).orElseThrow(UserNotFoundException::new);

    } catch (UserNotFoundException e) {
      log.error(" [ERROR] Error while loading UserServiceImpl {findUserDTOById} {}", e.getMessage());
    }

    return this.validatorUtil.getDTOFromEntity(userEntity, UserDTO.class);
  }

  @Override
  public boolean create(UserDTO userDTO) {
    log.info(" [INFO] Loading UserServiceImpl { create } ");
    try {
      this.userRepository.save(this.validatorUtil.getEntityFromDTO(userDTO, UserEntity.class));

      return true;
    } catch (Exception e) {
      log.error(" [ERROR] Error while loading UserServiceImpl { create } {} ", e.getMessage());
      return false;
    }
  }

  @Override
  public boolean deleteUser(UserDTO userDTO) {
    log.info(" [INFO] Loading UserServiceImpl { deleteUser } ");
    try {
      this.userRepository.findById(userDTO.getId())
          .ifPresentOrElse(e -> e.setDeleted(true), UserNotFoundException::new);

      return true;
    } catch (UserNotFoundException e) {

      log.error(" [ERROR] Error while loading UserServiceImpl {deleteUser} ");
      return false;
    }
  }

  @Override
  public boolean updateUser(UserDTO userDTO) {
    log.info(" [INFO] Loading UserServiceImpl { updateUser }");
    try {
      this.userRepository.save(this.validatorUtil.getEntityFromDTO(userDTO, UserEntity.class));

      return true;
    } catch (Exception e) {
      log.error(" [ERROR] Error while loading UserServiceImpl { updateUser } {}", e.getMessage());

      return false;
    }
  }

}
