package com.example.web.repository;

import com.example.web.model.entity.TourOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourOfferRepository extends JpaRepository<TourOfferEntity, Long> {

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.creationDate")
    List<Optional<TourOfferEntity>> findAll_TourOffers_ByDate();
}
