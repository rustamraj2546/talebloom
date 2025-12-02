package com.rkumar.talebloom.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StoryDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 30, message = "Content must be at least 30 characters long")
    private String content;

    @NotBlank(message = "Content Language is required")
    @Size(min = 5, message = "Content Language must be at least 5 characters long")
    private String contentLanguage;

    private  String category;

    private Set<String> tags = new HashSet<>();

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;
}
