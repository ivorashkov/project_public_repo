package com.example.web.repository;

import com.example.web.model.entity.TourOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToursRepository extends JpaRepository<TourOfferEntity, Long> {

}
