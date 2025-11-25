package com.rkumar.talebloom.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
public class StoryResDto {
    private Long id;

    private String title;

    private String content;

    private Long viewCount;

    private String contentLanguage;

    private String authorName;

    private  String category;

    private Set<String> tags = new HashSet<>();

    private LocalDateTime publishedAt;

    private LocalDateTime updatedAt;

}
