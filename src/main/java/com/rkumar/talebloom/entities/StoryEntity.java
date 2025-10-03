package com.rkumar.talebloom.entities;

import com.rkumar.talebloom.entities.type.Languages;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "STORY")
@Data
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private Languages contentLanguage;

    private Long viewCount;

    @CreationTimestamp
    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;


    private Long authorId;

    private Long categoryId;

    private Long slug;  // TAG
}
