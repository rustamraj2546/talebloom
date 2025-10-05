package com.rkumar.talebloom.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<StoryEntity> stories = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
