package com.example.web.model.requestDto;

import com.example.web.model.dto.BaseDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserLoginDTO extends BaseDTO {

  private String email;
  private String password;
}
