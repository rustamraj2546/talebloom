package com.rkumar.talebloom.entities;

import com.rkumar.talebloom.entities.type.Languages;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    //  Owing side BUT child entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author", nullable = false)
    private UserEntity author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @ManyToMany
    @JoinTable(
            name = "story_tag",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();  // TAG FK

    @OneToMany(mappedBy = "storyId")
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "bookmarkedStoryId")
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "reportedStoryId")
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "storyId")
    private List<ViewHistoryEntity> viewHistories = new ArrayList<>();

    @OneToMany(mappedBy = "storyId")
    private List<CommentEntity> comments = new ArrayList<>();
}
