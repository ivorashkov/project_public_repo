package com.example.web.util;

import com.example.web.model.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface ValidatorUtil {

    <E> boolean isValid(E entity);

    <E extends UserEntity> boolean isAdmin(E entity);

    <E extends UserEntity> boolean isActive(E entity);

    <E> ResponseEntity<E> responseEntity(E entity);

}
