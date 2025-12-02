package com.rkumar.talebloom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BioDto {
    private Long userId;

    @NotBlank(message = "Bio is required")
    @Size(min = 5, message = "Bio must be at least 5 characters long")
    private String bio;
}
