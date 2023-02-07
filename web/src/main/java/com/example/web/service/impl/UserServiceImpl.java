package com.example.web.service.impl;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.dto.UserLoginDTO;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper mapper;

  @Override
  public UserDTO findUserById(Long id) {
    return this.mapper.map(this.userRepository.findById(id).orElse(null), UserDTO.class);
  }

  @Override
  public void create(UserDTO userDTO) {
    this.userRepository.save(this.mapper.map(userDTO, UserEntity.class));
  }

  @Override
  public void deleteUser() {

  }

  @Override
  public void updateUser() {

  }

  @Override
  public boolean login(UserLoginDTO userLoginDTO) {

    if (!Objects.isNull(this.userRepository.findByEmailAddress(userLoginDTO.getUsername()))
        || !Objects.isNull(this.userRepository.findByUsername(userLoginDTO.getUsername()))) {
      return true;
    }

    return false;
  }
}
