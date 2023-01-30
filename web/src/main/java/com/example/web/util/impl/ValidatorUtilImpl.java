package com.example.web.util.impl;

import com.example.web.model.entity.UserEntity;
import com.example.web.model.enums.RoleType;
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

    /**Works**/
    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }

    /**Works**/
    @Override
    public <E extends UserEntity> boolean isAdmin(E entity) {
        //validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("ivo").orElse(null));
        return entity.getRole().getRoleName().equals(RoleType.admin);
    }

    /**Works**/
    @Override
    //validatorUtil.isActive(userRepository.findUserEntityByFirstName("ivo").orElse(null));
    public <E extends UserEntity> boolean isActive(E entity) {
        return entity.isActive();
    }
}
