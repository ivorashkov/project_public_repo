package com.example.web.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tour_offer_pictures")
public class OfferDataEntity extends BaseEntity {

  @Column(name = "picture_uri", nullable = false, unique = true)
  private String pictureLocation;

//  @ManyToOne(targetEntity = TourOfferEntity.class)
//  @JoinColumn(name = "offer_id", referencedColumnName = "id")
//  private TourOfferEntity tourOfferEntity;
}
