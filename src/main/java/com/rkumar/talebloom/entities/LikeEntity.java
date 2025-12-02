package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "LIKE_TABLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    enum targetType;

    @ManyToOne(fetch = FetchType.LAZY)  // user who liked story
    private UserEntity user;    // FK

    @ManyToOne
    @JoinColumn(name = "story_id")  // Story liked by user
    private StoryEntity story;   // FK

//    private Long comment;   // FK

    @CreationTimestamp
    private LocalDateTime createdAt;
}
