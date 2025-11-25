package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.dto.BioDto;
import com.rkumar.talebloom.dto.ProfilePicDto;
import com.rkumar.talebloom.dto.StoryResDto;
import com.rkumar.talebloom.dto.UserProfileDto;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(path = "/{userId}")
    ResponseEntity<UserProfileDto> getUserById(@PathVariable Long userId) {
        UserProfileDto userProfileDto = userService.getUserById(userId);
        return ResponseEntity.ok(userProfileDto);
    }

    /*
    * Update profile Bio
    * */
    @PutMapping(path = "/bio")
    ResponseEntity<UserProfileDto> updateProfileBio(@RequestBody BioDto bioDto) {
        UserProfileDto userProfileDto = userService.updateProfileBio(bioDto.getBio(), bioDto.getUserId());
        return ResponseEntity.ok(userProfileDto);
    }

    /*
    * Delete profile Bio
    * */
    @DeleteMapping(path = "/bio")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProfileBio(@RequestParam(name = "userId") Long userId) {
        boolean isDeleted = userService.deleteProfileBio(userId);
        if(!isDeleted){
            throw new ResourceNotFoundException("Profile Bio not found for user " + userId);
        }
    }

    /*
    * update profile DP
    * */
    @PutMapping(path = "/profilePic")
    ResponseEntity<UserProfileDto> updateProfilePic(@RequestBody ProfilePicDto profilePicDto) {
        UserProfileDto userProfileDto = userService.updateProfilePic(profilePicDto.getProfilePic(), profilePicDto.getUserId());
        return ResponseEntity.ok(userProfileDto);
    }

    /*
    * delete profile DP
    * */
    @DeleteMapping(path = "/profilePic")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProfilePic(@RequestParam(name = "userId") Long userId) {
        boolean isDeleted = userService.deleteProfilePic(userId);
        if(!isDeleted) {
            throw new ResourceNotFoundException("Profile Pic not found for user " + userId);
        }
    }

    /*
    * Bookmark a story by its ID (storyId)
    * */
    @GetMapping(path = "/bookmark/{storyId}")
    ResponseEntity<Boolean> bookmarkStoryById(@PathVariable Long storyId, @RequestParam(name = "userId") Long userId) {
        boolean isBookmarked = userService.bookmarkStoryById(storyId, userId);
        return new ResponseEntity<>(isBookmarked, HttpStatus.CREATED);
    }

    /*
    * get all bookmarked stories by user ID (userId)
    * */
    @GetMapping(path = "/bookmark")
    ResponseEntity<List<StoryResDto>> getBookmarkedStories(@RequestParam(name = "userId") Long userId) {
        List<StoryResDto> bookmarkedStories = userService.getBookmarkedStories(userId);
        return ResponseEntity.ok(bookmarkedStories);
    }

    /*
    * undo bookmarking a story by its ID (storyId)
    * */
    @DeleteMapping(path = "/bookmark/{storyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteBookmarkedStoryById(@PathVariable(name = "storyId") Long storyId, @RequestParam(name = "userId") Long userId) {
        boolean isDeleted = userService.deleteBookmarkedStoryById(storyId, userId);
        if (!isDeleted) {
            throw new ResourceNotFoundException("Bookmark not found for user " + userId + " and story " + storyId);
        }
    }

}
