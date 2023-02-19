package com.example.web.model.dto;

import com.example.web.model.interfaces.DTO;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements DTO {

  @Expose
  private Long id;

  @Expose
  private boolean isActive;

  @Expose
  private String firstName;

  @Expose
  private String lastName;

  @Expose
  private String email;

  @Expose
  private String username;

  @Expose
  private RoleDTO role;


}
