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

    Optional<UserEntity> findByEmailAddress(String email);

    Optional<UserEntity> findByUsername(String username);


}
