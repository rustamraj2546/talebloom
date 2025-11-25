package com.rkumar.talebloom.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StoryCreatedResDto {
    private Long id;

    private String title;

    private String contentLanguage;

    private  String category;

    private Set<String> tags = new HashSet<>();
}
