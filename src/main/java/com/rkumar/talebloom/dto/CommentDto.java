package com.rkumar.talebloom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotBlank(message = "description can't be NULL")
    private String comment;

    private Long storyId;
    private Long userId;
}
