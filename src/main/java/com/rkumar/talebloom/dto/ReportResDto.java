package com.rkumar.talebloom.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReportResDto {
    private Long reportId;
    private String report;
}
