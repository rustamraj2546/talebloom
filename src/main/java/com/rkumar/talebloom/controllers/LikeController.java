package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import com.rkumar.talebloom.services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    /*
    * Like a story by its ID (storyId)
    * */
    @PostMapping(path = "/{storyId}")
    ResponseEntity<Boolean> likeStoryById(@PathVariable Long storyId, @RequestParam(name = "userId") Long userId) {
        boolean isLiked = likeService.likeStoryById(storyId, userId);
        return new ResponseEntity<>(isLiked, HttpStatus.CREATED);
    }

    /*
    * Unlike a story by its ID (storyId)
    * */
    @DeleteMapping(path = "/{storyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void unlikeStoryById(@PathVariable(name = "storyId") Long storyId, @RequestParam(name = "userId") Long userId) {
        boolean isUnliked = likeService.unlikeStoryById(storyId, userId);
        if (!isUnliked) {
            throw new ResourceNotFoundException("Like not found for user " + userId + " and story " + storyId);
        }
    }

}
