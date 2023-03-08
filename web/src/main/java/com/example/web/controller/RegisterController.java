package com.example.web.controller;

import com.example.web.model.dto.UserDTO;
import com.example.web.model.requestDto.UserRegistrationDTO;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/register")
public class RegisterController {

  private final UserService userService;

  @PostMapping("")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO){


    return null;
  }

}
