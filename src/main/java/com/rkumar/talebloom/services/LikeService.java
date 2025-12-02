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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final StoryRepository storyRepository;

    LikeEntity isAlreadyLiked(StoryEntity story, UserEntity user) {
        Optional<LikeEntity> likeEntity = likeRepository.findByStoryAndUser(story, user);
        return likeEntity.orElse(null);

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

        if (likeRepository.existsByStoryAndUser(storyEntity, userEntity)) {
            return true;    // already liked
        }

        LikeEntity likeEntity = LikeEntity.builder()
                .story(storyEntity)
                .user(userEntity)
                .build();

        LikeEntity likedEntity = likeRepository.save(likeEntity);
//        storyEntity.setLikes();   // bidirectional consistency maintenance
//        userEntity.setLikes();
        return true;
    }

    /*
     * unlike the liked story
     * */
    public boolean unlikeStoryById(Long storyId, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId)
        );

        StoryEntity storyEntity = storyRepository.findById(storyId).orElseThrow(
                () -> new ResourceNotFoundException("Story not found with id: " + storyId)
        );

        LikeEntity likeEntity = isAlreadyLiked(storyEntity, userEntity);
        if (likeEntity != null) {
            likeRepository.deleteById(likeEntity.getId());
            return true;
        }

        return false;
    }
}
