package com.rkumar.talebloom.services;

import com.rkumar.talebloom.entities.LikeEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.repositories.LikeRepository;
import com.rkumar.talebloom.repositories.StoryRepository;
import com.rkumar.talebloom.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    boolean isAlreadyLiked(StoryEntity story, UserEntity user) {
        return likeRepository.existsByStoryAndUser(story, user);
    }

    /*
    * Like a story by its ID (storyId)
    * */
    @Transactional
    public boolean likeStoryById(Long storyId, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId)
        );

        StoryEntity storyEntity = storyRepository.findById(storyId).orElseThrow(
                () -> new ResourceNotFoundException("Story not found with id: " + storyId)
        );

        if (isAlreadyLiked(storyEntity, userEntity)) {
            return true;
        }

        LikeEntity likeEntity = LikeEntity.builder()
                .story(storyEntity)
                .user(userEntity)
                .build();

        LikeEntity likedEntity = likeRepository.save(likeEntity);
//        storyEntity.setLikes();   // bidirectinal consistency mentenance
//        userEntity.setLikes();
        return true;
    }

    public boolean unlikeStoryById(Long storyId, Long userId) {
        throw new IllegalStateException("Method not implemented");
    }
}
