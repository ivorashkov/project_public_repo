package com.example.web.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false, unique = true, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @Column(nullable = false, unique = true, name = "email_address")
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

    @Column
    private int approvedBy;

    @Column(name = "user_star")
    private boolean hasStar;

    @OneToMany(targetEntity = OfficeEntity.class,mappedBy = "user", fetch = FetchType.LAZY)
    private List<OfficeEntity> offices;

    @OneToMany(targetEntity = TourOfferEntity.class, mappedBy = "user", fetch = FetchType.LAZY)
    private List<TourOfferEntity> offers;

    @OneToOne
    private AdditionalInfoEntity additionalInfo;

}
