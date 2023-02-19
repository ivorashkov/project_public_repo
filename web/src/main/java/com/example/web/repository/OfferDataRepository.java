package com.example.web.repository;

import com.example.web.model.entity.OfferDataPathEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDataRepository extends JpaRepository<OfferDataPathEntity, Long> {


  Optional<List<OfferDataPathEntity>> findByOfferId(Long offerId);

  Optional<List<OfferDataPathEntity>> findAllByOfferId(Long id);
}
