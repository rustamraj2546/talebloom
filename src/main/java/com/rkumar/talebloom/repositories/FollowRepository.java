package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long> {
}