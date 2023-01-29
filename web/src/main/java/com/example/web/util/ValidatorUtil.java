package com.example.web.util;

public interface ValidatorUtil {

    <E> boolean isAdmin(E entity);

    <E> boolean isValid(E entity);

    <E> boolean isActive(E entity);

}
