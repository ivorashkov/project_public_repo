package com.example.web.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "offices")
public class OfficeEntity extends BaseEntity {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    private RegularUserEntity user;

    public void setUser(RegularUserEntity user) {
        this.user = user;
    }

    public RegularUserEntity getUser() {
        return user;
    }

    public OfficeEntity() {
    }

    public OfficeEntity(String country, String city, String address, String phoneNumber, RegularUserEntity user) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public String getCountry() {
        return country;
    }

    public OfficeEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public OfficeEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OfficeEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OfficeEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
