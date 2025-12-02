package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.dto.StoryCreatedResDto;
import com.rkumar.talebloom.dto.StoryResDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rkumar.talebloom.dto.StoryDto;
import com.rkumar.talebloom.services.StoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/story")
@RequiredArgsConstructor
public class StoryController {
    private final StoryService storyService;

    /*
    *  Author a new Story
    * */
    @PostMapping(path = "/create/{authorId}")
    ResponseEntity<StoryCreatedResDto> createStory(@Valid @RequestBody StoryDto storyDto, @PathVariable Long authorId) {
        StoryCreatedResDto savedStory = storyService.createStory(storyDto, authorId);
        return new ResponseEntity<>(savedStory, HttpStatus.CREATED);
    }

    /*
    *  fetch story using storyId
    * */
    @GetMapping(path = "/{storyId}")
    ResponseEntity<StoryResDto> getStoryById(@PathVariable Long storyId) {
        StoryResDto story = storyService.getStoryById(storyId);
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    /*
    * fetch All stories
    * */
    @GetMapping(path = "/all")
    ResponseEntity<List<StoryResDto>> getAllStories(@RequestParam(name = "pageNo") int pageNo) {
        int pageSize = 3;
        List<StoryResDto> allStories = storyService.getAllStories(pageNo, pageSize);

        return new ResponseEntity<>(allStories, HttpStatus.OK);
    }

    @GetMapping(path = "/by-tag")
    ResponseEntity<List<StoryResDto>> getStoriesByTag(@RequestParam(name = "tagName") String tags) {
        List<StoryResDto> stories = storyService.getStoriesByTag(tags);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    @GetMapping(path = "/by-category")
    ResponseEntity<List<StoryResDto>> getStoriesByCategory(@RequestParam(name = "category") String category) {
        List<StoryResDto> stories = storyService.getStoryByCategory(category);
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }
}
