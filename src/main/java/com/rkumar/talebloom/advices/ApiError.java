package com.rkumar.talebloom.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private String message;
    private HttpStatus status;
    private LocalDateTime localDateTime;

    public ApiError(){
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus status, LocalDateTime localDateTime) {
        this();
        this.message = message;
        this.status = status;
    }
}
