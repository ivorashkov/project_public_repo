package com.example.web.util.impl;

import com.example.web.model.entity.BaseEntity;
import com.example.web.model.entity.UserEntity;
import com.example.web.model.enums.RoleType;
import com.example.web.util.ValidatorUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUtilImpl implements ValidatorUtil {

  private final Validator validator;
  private final ModelMapper mapper;

  public ValidatorUtilImpl(ModelMapper mapper) {
    this.mapper = mapper;
    this.validator = Validation
        .buildDefaultValidatorFactory()
        .getValidator();
  }

  /**
   * Works
   **/
  @Override
  public <E> boolean isValid(E entity) {
    return this.validator.validate(entity).isEmpty();
  }

  /**
   * Works
   **/
  @Override
  public <E extends UserEntity> boolean isAdmin(E entity) {
    //validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("ivo").orElse(null));
    return entity.getRole().getRoleName().equals(RoleType.admin);
  }

  /**
   * Works
   **/
  @Override
  //validatorUtil.isActive(userRepository.findUserEntityByFirstName("ivo").orElse(null));
  public <E extends UserEntity> boolean isActive(E entity) {
    return entity.isActive();
  }


  /**
   * Works
   **/
  @Override
  public <E> ResponseEntity<E> responseEntity(E entity) {

    if (entity == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(entity);
    }
  }

  /**
   * Works
   **/
  @Override
  public <T, D> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
    return entities.map(objectEntity -> this.mapper.map(objectEntity, dtoClass));
  }

  /**
   * Works
   **/
  @Override
  public <E> E getCriteriaParam(E country, E city) {
    if (country != null) {
      return country;
    } else if (city != null) {
      return city;
    }
    return null;
  }

  /**
   * returns Entity if it's not deleted, else it returns null
   */
  @Override
  public <E extends BaseEntity> E isDeleted(E entity) {
    return entity.isDeleted() ? null : entity;
  }

}
