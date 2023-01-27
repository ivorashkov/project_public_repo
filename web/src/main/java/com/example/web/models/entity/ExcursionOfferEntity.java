package com.example.web.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class ExcursionOfferEntity extends BaseEntity {

    @Column(nullable = false, name = "country")
    private String targetCountry;

    @Column(nullable = false, name = "city")
    private String targetCity;

    @Column(nullable = false)
    @Min(1)
    @Max(365)
    private int durationInDays;

    @Column(nullable = false, name = "stars")
    @Min(0)
    @Max(7)
    private double starsOfHotel;

    @Column(nullable = false)
    @Min(1)
    private BigDecimal price;

    @Column(nullable = false)
    private String description;

    //cascade type??
    @ManyToOne
    private RegularUserEntity user;

    public ExcursionOfferEntity() {
    }

    public ExcursionOfferEntity(String targetCountry,
                                String targetCity,
                                int durationInDays,
                                double starsOfHotel,
                                BigDecimal price,
                                String description
    ) {
        this.targetCountry = targetCountry;
        this.targetCity = targetCity;
        this.durationInDays = durationInDays;
        this.starsOfHotel = starsOfHotel;
        this.price = price;
        this.description = description;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public ExcursionOfferEntity setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry;
        return this;
    }

    public String getTargetCity() {
        return targetCity;
    }

    public ExcursionOfferEntity setTargetCity(String targetCity) {
        this.targetCity = targetCity;
        return this;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public ExcursionOfferEntity setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
        return this;
    }

    public double getStarsOfHotel() {
        return starsOfHotel;
    }

    public ExcursionOfferEntity setStarsOfHotel(double starsOfHotel) {
        this.starsOfHotel = starsOfHotel;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ExcursionOfferEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ExcursionOfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
