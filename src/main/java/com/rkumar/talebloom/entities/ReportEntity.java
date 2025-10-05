package com.rkumar.talebloom.entities;

import com.rkumar.talebloom.entities.type.ReportStatus;
import com.rkumar.talebloom.entities.type.TargetTypes;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPORT")
@Data
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TargetTypes targetType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status;

    @Column(nullable = false)
    private String reason;


    private String adminNotes;

    // FK
    @ManyToOne
    @JoinColumn(name = "reporter_user_id")
    private UserEntity reporterUserId;

    private Long reportedUserId;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity reportedStoryId;

    @ManyToOne
    @JoinColumn(name = "reported_comment_id")
    private CommentEntity reportedCommentId;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

}
