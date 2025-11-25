package com.rkumar.talebloom.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class StoryDto {
    private Long id;

    private String title;

    private String content;

    private String contentLanguage;

    private  String category;

    private Set<String> tags = new HashSet<>();

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;
}
