package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.dto.ReportCommentDto;
import com.rkumar.talebloom.dto.ReportResDto;
import com.rkumar.talebloom.dto.ReportStoryDto;
import com.rkumar.talebloom.services.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping(path = "/story/{storyId}")
    public ResponseEntity<ReportResDto> reportStoryById(@PathVariable Long storyId, @RequestParam(name = "userId") Long userId, @Valid @RequestBody ReportStoryDto reportStory) {
        ReportResDto reported = reportService.reportStoryById(storyId, userId, reportStory);
        return ResponseEntity.ok(reported);
    }

    @PostMapping(path = "/comment/{commentId}")
    public ResponseEntity<ReportResDto> repotCommentById(@PathVariable Long commentId, @RequestParam(name = "userId") Long userId, @Valid @RequestBody ReportCommentDto reportComment) {
        ReportResDto reported = reportService.reportCommentById(commentId, userId, reportComment);
        return ResponseEntity.ok(reported);
    }
}
