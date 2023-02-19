package com.example.web.service;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.dto.UserLoginDTO;
import com.example.web.model.entity.UserEntity;
import java.util.Optional;

public interface UserService {

  UserDTO findUserById(Long id);

  void create(UserDTO userDTO);

  boolean deleteUser();

  boolean updateUser();

  boolean login(UserLoginDTO userLoginDTO);

  UserEntity findById(long id);
}
