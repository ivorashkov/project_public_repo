package com.example.web.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class TourOfferEntity extends BaseEntity {

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

    @Column(nullable = true)
    private double discount;

    @ManyToOne
    private RegularUserEntity user;

}
