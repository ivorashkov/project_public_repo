package com.example.web.service;

import com.example.web.model.dto.UserDTO;

public interface UserService {

  UserDTO findUserDTOById(Long id);

  boolean create(UserDTO userDTO);

  boolean deleteUser(UserDTO userDTO);

  boolean updateUser(UserDTO userDTO);

}
