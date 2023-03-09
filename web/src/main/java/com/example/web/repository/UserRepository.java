package com.example.web.repository;

import com.example.web.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
  Optional<UserEntity> findUserEntityById(@Param("id") Long id);

  @Query("SELECT u FROM UserEntity u "
      + " WHERE u.email LIKE CONCAT('%',:query, '%')"
      + " AND u.isDeleted = false")
  Optional<UserEntity> findUserEntityByEmail(@Param("query") String email);

  boolean existsByEmail(String email);

}
