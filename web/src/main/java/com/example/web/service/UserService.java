package com.example.web.service;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.requestDto.UserLoginRequestDTO;

public interface UserService {

  UserDTO findUserDTOById(Long id);

  boolean create(UserDTO userDTO);

  boolean deleteUser(UserDTO userDTO);

  boolean updateUser(UserDTO userDTO);

  boolean login(UserLoginRequestDTO userLoginDTO);

}
