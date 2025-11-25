package com.rkumar.talebloom.services;

import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rkumar.talebloom.dto.CommentDto;
import com.rkumar.talebloom.entities.CommentEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.repositories.CommentRepository;
import com.rkumar.talebloom.repositories.StoryRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    public CommentDto createComment(CommentDto comment, Long userId, Long storyId) {
        CommentEntity commentToBeCreated = modelMapper.map(comment, CommentEntity.class);

        StoryEntity story = storyRepository.findById(storyId).orElseThrow(
            () -> new ResourceNotFoundException("Story not found with id: '" + storyId)
        );

        UserEntity userWhoCommented = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: '" + userId)
        );

        commentToBeCreated.setStory(story);
        commentToBeCreated.setUser(userWhoCommented);

        CommentEntity savedComment = commentRepository.save(commentToBeCreated);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    public List<CommentDto> getCommentsByStoryId(Long storyId) {
        List<CommentEntity> commentsList = commentRepository.findAllCommentsByStoryId(storyId);

        List<CommentDto> commentsResList = commentsList.stream()
                .map(commentEntity -> {
                    return modelMapper.map(commentEntity, CommentDto.class);
                })
                .collect(Collectors.toList());

        return commentsResList;
    }

    public CommentDto updateCommentById(CommentDto inputData, Long commentId, Long userId) {
        CommentEntity commentEntity = isExistCommentIdWithUserId(commentId, userId);

        commentEntity.setComment(inputData.getComment());

        CommentEntity updatedComment = commentRepository.save(commentEntity);

        return modelMapper.map(updatedComment, CommentDto.class);
    }


    public Boolean deleteCommentById(Long commentId, Long userId) {
        CommentEntity commentEntity = isExistCommentIdWithUserId(commentId, userId);

        commentRepository.deleteById(commentId);

        return true;
    }


    private CommentEntity isExistCommentIdWithUserId(Long commentId, Long userId) {
        Optional<CommentEntity> commentEntity = commentRepository.findByIdAndUserId(commentId, userId);
        if (commentEntity.isEmpty()) {
            throw new ResourceNotFoundException("Comment with commentId: " + commentId + " Does not commented by userId: " + userId);
        }

        return commentEntity.get();
    }
}
