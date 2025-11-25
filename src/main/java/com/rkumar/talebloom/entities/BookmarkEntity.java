package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "BOOKMARK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity bookmarkedByUser;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private StoryEntity bookmarkedStory;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
