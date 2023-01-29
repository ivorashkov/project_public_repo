package com.example.web.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseUserEntity extends BaseEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, name = "email_address")
    @Email
    private String emailAddress;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @Enumerated(EnumType.STRING)
    private RoleEntity role;

    //todo check if should be here or in the UserEntity
    public BaseUserEntity addRole(RoleEntity userRole){
        this.role = userRole;
        return this;
    }
}
