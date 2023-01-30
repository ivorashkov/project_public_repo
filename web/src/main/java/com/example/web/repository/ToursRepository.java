package com.example.web.repository;

import com.example.web.model.entity.TourOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToursRepository extends JpaRepository<TourOfferEntity, Long> {

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.creationDate ASC")
    List<Optional<TourOfferEntity>> findAllNewTourOffersOrderByDateAsc();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.creationDate DESC")
    List<Optional<TourOfferEntity>> findAllNewTourOffersOrderByDateDESC();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.targetCountry DESC")
    List<Optional<TourOfferEntity>> findAllTourOffersByCountryOrderByCountryDESC();

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.targetCountry ASC")
    List<Optional<TourOfferEntity>> findAllTourOffersByCountryOrderByCountryASC();

   //todo can we create Generic query where we can additionally insert paramName and order By param

}
