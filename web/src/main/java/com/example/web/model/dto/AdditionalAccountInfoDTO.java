package com.example.web.model.dto;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalAccountInfoDTO {

  @Expose
  private Long id;

  @Expose
  private String path;

  @Expose
  private UserDTO user;

}
