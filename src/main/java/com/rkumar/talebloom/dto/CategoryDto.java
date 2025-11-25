package com.rkumar.talebloom.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDto {
    private Long id;

    private String categoryName;


    @JsonCreator
    public CategoryDto(@JsonProperty("categoryName") String categoryName) {
        this.categoryName = categoryName;
    }

}
