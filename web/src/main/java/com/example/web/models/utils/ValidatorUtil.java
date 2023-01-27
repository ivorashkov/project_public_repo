package com.example.web.models.utils;

public interface ValidatorUtil {

    <E> boolean isAdmin(E entity);

    <E> boolean isValid(E entity);

    <E> boolean isActive(E entity);

}
