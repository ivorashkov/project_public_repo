package com.example.web.service.impl;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.dto.UserLoginDTO;
import com.example.web.model.entity.UserEntity;
import com.example.web.repository.UserRepository;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ModelMapper mapper;

  @Override
  public UserDTO findUserDTOById(Long id) {
    return this.mapper.map(this.userRepository.findUserEntityById(id), UserDTO.class);
  }

  @Override
  public String create(UserDTO userDTO) {
    try{
      this.userRepository.save(this.mapper.map(userDTO, UserEntity.class));

      return "User " + userDTO.getUsername() + " created.";
    } catch (Exception e){

      return "Unable to create user " + e.getMessage();
    }
  }

  @Override
  public String deleteUser(UserDTO userDTO) {
    //todo check
    try{
      this.userRepository.findById(userDTO.getId()).orElse(null).setDeleted(true);

      return "User " + userDTO.getUsername() + " was deleted.";
    }catch (Exception e){

      return "Unable to delete user" + userDTO.getUsername() + " " + e.getMessage();
    }
  }

  @Override
  public String updateUser(UserDTO userDTO) {
    //todo check
    try {
      this.userRepository.save(this.mapper.map(userDTO, UserEntity.class));

      return "User " + userDTO.getUsername() + " saved.";
    } catch (Exception e) {

      return "User not saved " + e.getMessage();
    }
  }

  @Override
  public boolean login(UserLoginDTO userLoginDTO) {

    return false;
  }
}
