package com.example.web.model.dto;

import com.example.web.model.interfaces.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO implements DTO {

  //TODO LAST
  private String username;
  private String password;
}
