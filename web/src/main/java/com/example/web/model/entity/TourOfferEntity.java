package com.example.web.model.entity;

import com.example.web.model.enums.TransportType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offers")
public class TourOfferEntity extends BaseEntity {

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("false")
    private boolean isDeleted;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(nullable = false, name = "country")
    private String targetCountry;

    @Column(nullable = false, name = "city")
    private String targetCity;

    @Column(nullable = false, name = "duration_in_days")
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

    @Column
    private double discount;

    @ManyToOne
    private UserEntity user;

    @Column(name = "transport_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransportType transportType;
}
