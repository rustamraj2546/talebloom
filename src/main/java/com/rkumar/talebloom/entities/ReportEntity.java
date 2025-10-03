package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "REPORT")
@Data
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK
    private Long reporterId;
    private Long reportedUserId;
    private Long storyId;
    private Long reportedCommentId;

//    enum reportType;
//    enum status;
    private String reason;

    private String adminNotes;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;

}
