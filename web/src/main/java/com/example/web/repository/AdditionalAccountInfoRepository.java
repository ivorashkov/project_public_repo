package com.example.web.repository;

import com.example.web.model.entity.AdditionalAccountInfoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalAccountInfoRepository extends JpaRepository<AdditionalAccountInfoEntity, Long> {

  Optional<List<AdditionalAccountInfoEntity>> findAllByUserId(Long userId);
}
