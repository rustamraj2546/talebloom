package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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


    private Long storyId;   // FK

    private Long userId;    // FK

    private Long parentId;     // FK
}
