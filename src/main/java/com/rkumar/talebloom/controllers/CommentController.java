package com.rkumar.talebloom.controllers;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rkumar.talebloom.dto.CommentDto;
import com.rkumar.talebloom.services.CommentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(path = "/create/{userId}")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto comment, @PathVariable Long userId, @PathParam("storyId") Long storyId) {
        CommentDto savedComment = commentService.createComment(comment, userId, storyId);

        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    /*
     * Fetch All Comment on the story
     * */
    @GetMapping(path = "/{storyId}")
    public ResponseEntity<List<CommentDto>> getCommentsByStoryId(@PathVariable Long storyId) {
        List<CommentDto> commentsList = commentService.getCommentsByStoryId(storyId);

        return new ResponseEntity<>(commentsList, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{commentId}")
    public ResponseEntity<CommentDto> updateCommentById(@Valid @RequestBody CommentDto inputData, @PathVariable Long commentId, @PathParam("userId") Long userId) {
        CommentDto updatedComment = commentService.updateCommentById(inputData, commentId, userId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping(path = "/delete/{commentId}")
    public ResponseEntity<Boolean> deleteCommentById(@PathVariable Long commentId, @PathParam("userId") Long userId) {
        Boolean isDeleted = commentService.deleteCommentById(commentId, userId);
        return ResponseEntity.ok(isDeleted);
    }

}
