package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "LIKE_TABLE")
@Data
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    enum targetType;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;    // FK

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity storyId;   // FK

//    private Long commentId;   // FK

    @CreationTimestamp
    private LocalDateTime createdAt;
}
