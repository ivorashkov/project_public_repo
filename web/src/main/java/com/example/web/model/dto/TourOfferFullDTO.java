package com.example.web.model.dto;

import com.example.web.model.enums.TransportType;
import com.google.gson.annotations.Expose;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TourOfferFullDTO extends BaseDTO {

  @Expose
  private Long id;

  @Expose
  private String title;

  @Expose
  private String country;

  @Expose
  private String city;

  @Expose
  private int duration;

  @Expose
  private double stars;

  @Expose
  private BigDecimal price;

  @Expose
  private String description;

  @Expose
  private double discount;

  @Expose
  private UserDTO user;

  @Expose
  private TransportType transportType;

  @Expose
  private List<TourOfferFilePathDTO> paths;

  public TourOfferFullDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public TourOfferFullDTO setTitle(String title) {
    this.title = title;
    return this;
  }

  public TourOfferFullDTO setCountry(String country) {
    this.country = country;
    return this;
  }

  public TourOfferFullDTO setCity(String city) {
    this.city = city;
    return this;
  }

  public TourOfferFullDTO setDuration(int duration) {
    this.duration = duration;
    return this;
  }

  public TourOfferFullDTO setStars(double stars) {
    this.stars = stars;
    return this;
  }

  public TourOfferFullDTO setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public TourOfferFullDTO setDescription(String description) {
    this.description = description;
    return this;
  }

  public TourOfferFullDTO setDiscount(double discount) {
    this.discount = discount;
    return this;
  }

  public TourOfferFullDTO setUser(UserDTO user) {
    this.user = user;
    return this;
  }

  public TourOfferFullDTO setTransportType(TransportType transportType) {
    this.transportType = transportType;
    return this;
  }

  public TourOfferFullDTO setPaths(
      List<TourOfferFilePathDTO> paths) {
    this.paths = paths;
    return this;
  }
}
