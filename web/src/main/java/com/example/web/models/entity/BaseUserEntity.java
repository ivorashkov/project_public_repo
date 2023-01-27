package com.example.web.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseUserEntity extends BaseEntity {

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, name = "email_address")
    private String emailAddress;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, unique = true, name = "phone_number")
    private String phoneNumber;

    public BaseUserEntity() {
    }

    public BaseUserEntity(String username,
                          String password,
                          String emailAddress,
                          String firstName,
                          String lastName,
                          String phoneNumber
    ) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public BaseUserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public BaseUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public BaseUserEntity setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public BaseUserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BaseUserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BaseUserEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
