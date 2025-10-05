package com.rkumar.talebloom.entities;

import com.rkumar.talebloom.entities.type.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    private String gender;
    private Integer age;

    private String bio;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Roles role;

    // inverse side BUT Parent entity
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<StoryEntity> stories = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "bookmarkedByUserId")
    private List<BookmarkEntity> bookMarks = new ArrayList<>();

    @OneToMany(mappedBy = "reporterUserId")
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<ViewHistoryEntity> viewHistories = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    private List<CommentEntity> comments = new ArrayList<>();

}
