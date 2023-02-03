package com.example.web.repository;

import com.example.web.model.entity.TourOfferEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourOfferRepository extends PagingAndSortingRepository<TourOfferEntity, Long> {

    @Query("SELECT t FROM TourOfferEntity t ORDER BY t.creationDate")
    Slice<Optional<TourOfferEntity>> findAll_TourOffers_ByDate();

    List<Optional<TourOfferEntity>> findAll();
}
