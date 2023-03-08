package com.example.web.model.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
  @Size(min = 4, max = 10)
  private String username;

  @Email
  private String email;

  @Size(min = 1)
  private String firstName;

  @Size(min = 1)
  private String lastName;

  @Size(min = 8, max = 15)
  private String password;

  @Size(min = 8, max = 15)
  private String passwordConfirm;

}
