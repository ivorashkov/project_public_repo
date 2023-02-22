package com.example.web.model.dto;

import com.example.web.model.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO implements DTO {

  private boolean isDeleted;

  @Override
  public boolean isDeleted() {
    return isDeleted;
  }
}
