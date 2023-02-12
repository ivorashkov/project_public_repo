package com.example.web.repository;

import com.example.web.model.entity.OfferDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferDataRepository extends JpaRepository<OfferDataEntity, Long> {


}
