package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.LikeEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByStoryAndUser(StoryEntity story, UserEntity user);
}