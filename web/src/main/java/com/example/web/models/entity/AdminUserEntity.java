package com.example.web.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_users")
public class AdminUserEntity extends BaseUserEntity {

    @OneToOne
    private RoleEntity roleEntity;

}
