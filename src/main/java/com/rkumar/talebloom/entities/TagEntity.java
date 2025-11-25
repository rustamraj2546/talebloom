package com.rkumar.talebloom.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAG")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"stories"})
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100)
    private String tagName;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column(updatable = true)
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.LAZY)
    private Set<StoryEntity> stories = new HashSet<>();
}
