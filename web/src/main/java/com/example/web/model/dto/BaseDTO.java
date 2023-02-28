package com.example.web.model.dto;

import com.example.web.model.interfaces.DeletableObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO implements DeletableObject {

  private boolean isDeleted;

  @Override
  public boolean isDeleted() {
    return isDeleted;
  }
}
