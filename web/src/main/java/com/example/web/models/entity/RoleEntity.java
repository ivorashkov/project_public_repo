package com.example.web.models.entity;

import com.example.web.models.constants.enums.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private Roles roleName;

    public RoleEntity() {
    }

    public RoleEntity(Roles roleName) {
        this.roleName = roleName;
    }

    public Roles getRoleName() {
        return roleName;
    }

    public RoleEntity setRoleName(Roles roleName) {
        this.roleName = roleName;
        return this;
    }
}
