package com.rkumar.talebloom.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAG")
@Data
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tagName;


    @ManyToMany(mappedBy = "tags")
    private Set<StoryEntity> stories = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
