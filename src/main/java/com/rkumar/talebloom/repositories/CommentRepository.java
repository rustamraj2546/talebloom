package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findAllCommentsByStoryId(Long storyId);

    Optional<CommentEntity> findByIdAndUserId(Long commentId, Long userId);
}