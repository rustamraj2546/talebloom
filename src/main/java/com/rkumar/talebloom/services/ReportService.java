package com.rkumar.talebloom.services;

import com.rkumar.talebloom.dto.ReportCommentDto;
import com.rkumar.talebloom.dto.ReportResDto;
import com.rkumar.talebloom.dto.ReportStoryDto;
import com.rkumar.talebloom.entities.CommentEntity;
import com.rkumar.talebloom.entities.ReportEntity;
import com.rkumar.talebloom.entities.StoryEntity;
import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.entities.type.ReportStatus;
import com.rkumar.talebloom.entities.type.TargetTypes;
import com.rkumar.talebloom.repositories.CommentRepository;
import com.rkumar.talebloom.repositories.ReportRepository;
import com.rkumar.talebloom.repositories.StoryRepository;
import com.rkumar.talebloom.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private final StoryRepository storyRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ReportResDto reportStoryById(Long storyId, Long userId, ReportStoryDto reportStory) {
        StoryEntity reportedStory = storyRepository.findById(storyId).orElseThrow();
        UserEntity reporterUser = userRepository.findById(userId).orElseThrow();
        Long reportedUser = reportedStory.getAuthor().getId();

        ReportEntity report = ReportEntity.builder()
                .targetType(TargetTypes.STORY)
                .status(ReportStatus.OPEN)
                .reason(reportStory.getReason())
                .reporterUser(reporterUser)
                .reportedUser(reportedUser)
                .reportedStory(reportedStory)
                .build();

        ReportEntity savedReport = reportRepository.save(report);

        return ReportResDto.builder()
                .reportId(savedReport.getId())
                .report(savedReport.getReason())
                .build();
    }

    public ReportResDto reportCommentById(Long commentId, Long userId, ReportCommentDto reportComment) {
        CommentEntity reportedComment = commentRepository.findById(commentId).orElseThrow();
        UserEntity reporterUser = userRepository.findById(userId).orElseThrow();
        Long reportedUser = reportedComment.getUser().getId();

        ReportEntity report = ReportEntity.builder()
                .targetType(TargetTypes.COMMENT)
                .status(ReportStatus.OPEN)
                .reason(reportComment.getReason())
                .reporterUser(reporterUser)
                .reportedUser(reportedUser)
                .reportedComment(reportedComment)
                .build();

        ReportEntity savedReport = reportRepository.save(report);

        return ReportResDto.builder()
                .reportId(savedReport.getId())
                .report(savedReport.getReason())
                .build();
    }
}
