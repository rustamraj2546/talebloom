package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COMMENT")
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity storyId;   // FK

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;    // FK

//    private Long parentId;     // FK

    @OneToMany(mappedBy = "reportedCommentId", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReportEntity> reportedComments = new ArrayList<>();
}
