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

    private Long userId;
    private Long storyId;

//    private String ipAddress;

    private LocalDateTime viewedAt;
}
