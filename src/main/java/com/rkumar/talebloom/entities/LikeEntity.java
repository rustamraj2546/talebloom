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

    private Long userId;    // FK

    private Long storyId;   // FK

//    private Long commentId;   // FK

    @CreationTimestamp
    private LocalDateTime createdAt;
}
