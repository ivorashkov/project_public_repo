package com.example.web.service;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.dto.UserLoginDTO;
import com.example.web.model.entity.UserEntity;

public interface UserService {

  UserDTO findUserDTOById(Long id);

  String create(UserDTO userDTO);

  String deleteUser(UserDTO userDTO);

  String updateUser(UserDTO userDTO);

  boolean login(UserLoginDTO userLoginDTO);

}
