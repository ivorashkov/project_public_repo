package com.example.web.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "regular_users")
public class RegularUserEntity extends BaseUserEntity {

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

    @OneToMany(targetEntity = ExcursionOfferEntity.class, mappedBy = "user")
    private List<ExcursionOfferEntity> offers;

    @OneToOne
    private RoleEntity role;

}
