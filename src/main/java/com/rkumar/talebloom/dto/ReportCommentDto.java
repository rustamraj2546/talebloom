package com.rkumar.talebloom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportCommentDto {
    @NotBlank(message = "Reason is required")
    private String reason;
}
