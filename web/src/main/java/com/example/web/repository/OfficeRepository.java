package com.example.web.repository;

import com.example.web.model.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

    @Query("SELECT o FROM OfficeEntity o WHERE o.user.id = :userId ORDER BY o.city asc")
    List<Optional<OfficeEntity>> findAllOfficesByUserIdAsc(@Param("userId") Long userId);

    @Query("SELECT o FROM OfficeEntity o WHERE o.user.id = :userId ORDER BY o.city desc ")
    List<Optional<OfficeEntity>> findAllOfficesByUserIdDesc(@Param("userId") Long userId);

}
