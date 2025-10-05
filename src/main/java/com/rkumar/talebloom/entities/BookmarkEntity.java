package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKMARK")
@Data
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity bookmarkedByUserId;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity bookmarkedStoryId;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
