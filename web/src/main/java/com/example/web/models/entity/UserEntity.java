package com.example.web.models.entity;

import jakarta.persistence.*;
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
public class UserEntity extends BaseUserEntity {

    /**
     * Step 2:
     *  2 ID pictures and 1 selfie
     */

    /**
     * Step 3:
     */
    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false, unique = true, name = "credendial_number")
    private String credentialNumber;

    @Column(nullable = false, unique = true, name = "company")
    private String companyName;

    @Column(nullable = false, unique = true, name = "identifier_number")
    private String uniqueIdentifier;

    @Column(nullable = false)
    private String addressRegistration;

    @OneToMany(targetEntity = OfficeEntity.class, mappedBy = "user")
    private List<OfficeEntity> offices;

    @OneToMany(targetEntity = TourOfferEntity.class, mappedBy = "user")
    private List<TourOfferEntity> offers;

    @OneToOne
    private RoleEntity role;

}
