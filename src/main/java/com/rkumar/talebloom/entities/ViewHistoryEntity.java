package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "VIEW_HISTORY")
@Data
public class ViewHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private StoryEntity story;

//    private String ipAddress;

    private LocalDateTime viewedAt;
}
