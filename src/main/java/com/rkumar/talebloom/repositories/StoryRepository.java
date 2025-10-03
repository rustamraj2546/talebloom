package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.StoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
}