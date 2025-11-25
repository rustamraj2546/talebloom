package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.StoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
    Optional<StoryEntity> findByTitleAndAuthorId(String title, Long authorId);

    List<StoryEntity> findByTags_TagName(String tagName);

    List<StoryEntity> findByCategory_categoryName(String categoryName);
}