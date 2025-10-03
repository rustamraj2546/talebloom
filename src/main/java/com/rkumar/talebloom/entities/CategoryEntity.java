package com.rkumar.talebloom.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "CATEGORY")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String slug;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
