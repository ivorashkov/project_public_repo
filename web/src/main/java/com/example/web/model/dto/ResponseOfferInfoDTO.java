package com.example.web.model.dto;

import com.example.web.model.enums.TransportType;
import com.example.web.model.interfaces.DTO;
import com.google.gson.annotations.Expose;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOfferInfoDTO implements DTO {

  @Expose
  private Long id;

  @Expose
  private UserDTO user;

  @Expose
  private String title;

  @Expose
  private String date;

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
  private TransportType transportType;

  @Expose
  List<OfferDataPathDTO> paths;

}
