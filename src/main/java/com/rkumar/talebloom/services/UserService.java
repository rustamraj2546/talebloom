package com.rkumar.talebloom.services;


import com.rkumar.talebloom.dto.StoryResDto;
import com.rkumar.talebloom.dto.UserProfileDto;
import com.rkumar.talebloom.entities.BookmarkEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.repositories.BookmarkRepository;
import com.rkumar.talebloom.repositories.StoryRepository;
import com.rkumar.talebloom.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final StoryRepository storyRepository;


    UserEntity isExistUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("user with id: " + userId + " doesn't exist.")
        );
    }

    public UserProfileDto getUserById(Long userId) {
        UserEntity userEntity = isExistUserById(userId);

        return modelMapper.map(userEntity, UserProfileDto.class);
    }

    public UserProfileDto updateProfileBio(String bio, Long userId) {
        UserEntity userEntity = isExistUserById(userId);

        userEntity.setBio(bio);

        UserEntity updatedUser = userRepository.save(userEntity);

        return  modelMapper.map(updatedUser, UserProfileDto.class);
    }

    public UserProfileDto updateProfilePic(String profilePicUri, Long userId) {
        UserEntity userEntity = isExistUserById(userId);

        userEntity.setProfilePic(profilePicUri);

        UserEntity updatedUser = userRepository.save(userEntity);

        return  modelMapper.map(updatedUser, UserProfileDto.class);
    }

    public boolean deleteProfileBio(Long userId) {
        UserEntity userEntity = isExistUserById(userId);

        userEntity.setBio(null);

        UserEntity savedUser = userRepository.save(userEntity);

        return true;
    }

    public boolean deleteProfilePic(Long userId) {
        UserEntity userEntity = isExistUserById(userId);

        userEntity.setProfilePic(null);

        UserEntity savedUser = userRepository.save(userEntity);

        return true;
    }

    @Transactional
    public boolean bookmarkStoryById(Long storyId, Long userId) {
        UserEntity bookmarkedByUser = isExistUserById(userId);
        StoryEntity bookmarkedStory = storyRepository.findById(storyId).orElseThrow(
                () -> new ResourceNotFoundException("Story not found with id: " + storyId)
        );

        Optional<BookmarkEntity> isBookmarkAlreadyExist = bookmarkRepository.findByBookmarkedByUserAndBookmarkedStory(bookmarkedByUser, bookmarkedStory);
        if(isBookmarkAlreadyExist.isPresent()) {
            return true;
        }

        BookmarkEntity bookmarkEntity =  BookmarkEntity.builder()
                .bookmarkedByUser(bookmarkedByUser)
                .bookmarkedStory(bookmarkedStory)
                .build();

        BookmarkEntity savedBookmark = bookmarkRepository.save(bookmarkEntity);

        return true;
    }

    public List<StoryResDto> getBookmarkedStories(Long userId) {
        UserEntity user = isExistUserById(userId);
        List<BookmarkEntity> bookmarks = bookmarkRepository.findByBookmarkedByUser(user);

        return bookmarks.stream()
                .map(bookmarkEntity ->  {
                    StoryEntity storyEntity = bookmarkEntity.getBookmarkedStory();
                    return modelMapper.map(storyEntity, StoryResDto.class);
                })
                .toList();
    }

    @Transactional
    public boolean deleteBookmarkedStoryById(Long storyId, Long userId) {
        UserEntity bookmarkedByUser = isExistUserById(userId);
        StoryEntity bookmarkedStory = storyRepository.findById(storyId).orElseThrow(
                () -> new ResourceNotFoundException("Story not found with id: " + storyId)
        );

        long deletedCount = bookmarkRepository.deleteByBookmarkedByUserAndBookmarkedStory(bookmarkedByUser, bookmarkedStory);

        return deletedCount > 0;
    }
}
