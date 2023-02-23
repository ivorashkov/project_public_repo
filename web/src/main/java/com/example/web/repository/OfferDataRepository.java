package com.example.web.repository;

import com.example.web.model.entity.TourOfferImagePathEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDataRepository extends JpaRepository<TourOfferImagePathEntity, Long> {


  Optional<List<TourOfferImagePathEntity>> findByOfferId(Long offerId);

  Optional<List<TourOfferImagePathEntity>> findAllByOfferId(Long id);
}
