package com.example.web.model.entity;

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

    @Column(nullable = false)
    private boolean isActive;

    @Column
    private int approvedBy;

    @OneToMany(targetEntity = OfficeEntity.class, mappedBy = "id")
    private List<OfficeEntity> offices;

    @OneToMany(targetEntity = TourOfferEntity.class, mappedBy = "user")
    private List<TourOfferEntity> offers;

    @OneToOne
    private AdditionalInfoEntity additionalInfo;

}
