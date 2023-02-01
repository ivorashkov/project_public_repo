package com.example.web.service.impl;

import com.example.web.model.dto.UserDTO;
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
    public UserDTO findUserById(Long id) {
        return this.mapper.map(this.userRepository.findById(id).orElse(null), UserDTO.class);
    }

    @Override
    public UserEntity create(UserDTO userDTO) {
        return null;
    }


}
