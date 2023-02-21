package com.example.web.model.dto;

import com.example.web.model.interfaces.DTO;
import com.google.gson.annotations.Expose;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalAccountInfoDTO implements DTO {

  @Expose
  private Long id;

  @Expose
  private String documentLocation;

  @Expose
  @ManyToOne
  private UserDTO user;

}
