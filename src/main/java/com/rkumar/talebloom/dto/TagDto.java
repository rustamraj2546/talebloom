package com.rkumar.talebloom.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TagDto {
    private Long id;

    private String tagName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
