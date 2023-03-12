package com.example.web.model.requestDto;

import com.example.web.model.dto.BaseDTO;
import lombok.Data;

@Data
public class UserLoginRequestDTO extends BaseDTO {

  private String email;
  private String password;
}
