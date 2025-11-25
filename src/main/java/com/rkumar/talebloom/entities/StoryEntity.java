package com.rkumar.talebloom.entities;

import com.rkumar.talebloom.entities.type.Languages;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "STORY")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"tags", "likes", "bookmarks", "reports", "viewHistories", "comments"})
public class StoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Languages contentLanguage;

    private Long viewCount;

    @CreationTimestamp
    private LocalDateTime publishedAt;

    @CreationTimestamp
    @Column(updatable = true)
    private LocalDateTime updatedAt;


    //  Owing side BUT child entity
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author", nullable = false)
    private UserEntity author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "story_tag",
            joinColumns = @JoinColumn(name = "story_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();  // TAG FK

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<LikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "bookmarkedStory", fetch = FetchType.LAZY)
    private List<BookmarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "reportedStory", fetch = FetchType.LAZY)
    private List<ReportEntity> reports = new ArrayList<>();

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<ViewHistoryEntity> viewHistories = new ArrayList<>();

    @OneToMany(mappedBy = "story", fetch = FetchType.LAZY)
    private List<CommentEntity> comments = new ArrayList<>();
}
