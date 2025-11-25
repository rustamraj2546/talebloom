package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.BookmarkEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {

    @Query("SELECT b FROM BookmarkEntity b WHERE b.bookmarkedByUser = ?1")
    List<BookmarkEntity> findByBookmarkedByUser(UserEntity user);

    // It returns the number of rows deleted, which is useful for checking success.
    long deleteByBookmarkedByUserAndBookmarkedStory(UserEntity user, StoryEntity story);

    Optional<BookmarkEntity> findByBookmarkedByUserAndBookmarkedStory(UserEntity bookmarkedByUser, StoryEntity bookmarkedStory);

}