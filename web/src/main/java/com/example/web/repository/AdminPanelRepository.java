package com.example.web.repository;

import com.example.web.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPanelRepository extends JpaRepository<UserEntity, Long> {

}
