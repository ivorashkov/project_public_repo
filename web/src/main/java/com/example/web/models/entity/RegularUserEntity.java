package com.example.web.models.entity;

import jakarta.persistence.*;

import java.util.Set;

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

    @Column(nullable = false, unique = true, name = "credendial_number")
    private String credentialNumber;

    @Column(nullable = false, unique = true, name = "company")
    private String companyName;

    @Column(nullable = false, unique = true, name = "identifier_number")
    private String uniqueIdentifier;

    @Column(nullable = false)
    private String addressRegistration;

    @OneToMany(targetEntity = OfficeEntity.class, mappedBy = "user")
    private Set<OfficeEntity> offices;

    @OneToMany(targetEntity = ExcursionOfferEntity.class, mappedBy = "user")
    private Set<ExcursionOfferEntity> offers;

    @OneToOne
    private RoleEntity role;

    public RegularUserEntity() {
    }

    public RegularUserEntity(String username,
                             String password,
                             String emailAddress,
                             String firstName,
                             String lastName,
                             String phoneNumber,
                             String credentialNumber,
                             String companyName,
                             String uniqueIdentifier,
                             String addressRegistration,
                             Set<OfficeEntity> offices,
                             Set<ExcursionOfferEntity> offers,
                             RoleEntity role
    ) {
        super(username, password, emailAddress, firstName, lastName, phoneNumber);
        this.credentialNumber = credentialNumber;
        this.companyName = companyName;
        this.uniqueIdentifier = uniqueIdentifier;
        this.addressRegistration = addressRegistration;
        this.offices = offices;
        this.offers = offers;
        this.role = role;
    }

    public String getCredentialNumber() {
        return credentialNumber;
    }

    public RegularUserEntity setCredentialNumber(String credentialNumber) {
        this.credentialNumber = credentialNumber;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public RegularUserEntity setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public RegularUserEntity setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        return this;
    }

    public String getAddressRegistration() {
        return addressRegistration;
    }

    public RegularUserEntity setAddressRegistration(String addressRegistration) {
        this.addressRegistration = addressRegistration;
        return this;
    }

    public Set<OfficeEntity> getOffices() {
        return offices;
    }

    public RegularUserEntity setOffices(Set<OfficeEntity> offices) {
        this.offices = offices;
        return this;
    }

    public Set<ExcursionOfferEntity> getOffers() {
        return offers;
    }

    public RegularUserEntity setOffers(Set<ExcursionOfferEntity> offers) {
        this.offers = offers;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public RegularUserEntity setRole(RoleEntity role) {
        this.role = role;
        return this;
    }
}
