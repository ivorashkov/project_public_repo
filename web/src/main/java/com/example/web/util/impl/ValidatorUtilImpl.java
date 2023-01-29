package com.example.web.util.impl;

import com.example.web.util.ValidatorUtil;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

@Component
public class ValidatorUtilImpl implements ValidatorUtil {
    private final Validator validator;

    public ValidatorUtilImpl() {
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <E> boolean isAdmin(E entity) {
        //todo: should I make this method static or call it through constructor
        return false;
    }

    @Override
    public <E> boolean isActive(E entity) {
        //todo: should I make this method static or call it through constructor
        return false;
    }
}
