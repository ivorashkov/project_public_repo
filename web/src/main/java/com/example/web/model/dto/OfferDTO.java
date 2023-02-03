package com.example.web.model.dto;

import com.example.web.model.enums.TransportType;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfferDTO {

    @Expose
    private Long id;

    @Expose
    private String title;

    @Expose
    private String creationDate;

    @Expose
    private String targetCountry;

    @Expose
    private String targetCity;

    @Expose
    private int durationInDays;

    @Expose
    private double starsOfHotel;

    @Expose
    private BigDecimal price;

    @Expose
    private String description;

    @Expose
    private double discount;

    @Expose
    private TransportType transportType;

}
