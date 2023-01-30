package com.example.web.repository;

import com.example.web.model.entity.TourOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToursRepository extends JpaRepository<TourOfferEntity, Long> {

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.creationDate")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByDate();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.targetCountry ASC")
    List<Optional<TourOfferEntity>> findAllTourOffersByCountry();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.price ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByPrice();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.durationInDays ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByDuration();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.discount ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByDiscount();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.starsOfHotel ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByStars();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.transportType ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByTransportType();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.user.firstName ASC")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByUserFirstName();


}
