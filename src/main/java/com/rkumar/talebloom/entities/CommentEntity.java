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

    @Column(nullable = false)
    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
    * storyId on which user commented
    * */
    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private StoryEntity story;   // FK

    /*
    * user who commented on story
    * */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;    // FK

//    private Long parent;     // FK

    @OneToMany(mappedBy = "reportedComment", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReportEntity> reportedComments = new ArrayList<>();
}
