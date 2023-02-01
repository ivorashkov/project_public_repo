package com.example.web.service;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.entity.UserEntity;

public interface UserService {

    UserDTO findUserById(Long id);

    UserEntity create(UserDTO userDTO);

}
