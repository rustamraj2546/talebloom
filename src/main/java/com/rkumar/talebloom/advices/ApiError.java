package com.rkumar.talebloom.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiError {
    private String message;
    private HttpStatus status;
    private LocalDateTime localDateTime;

    public ApiError(){
        this.localDateTime = LocalDateTime.now();
    }

    public ApiError(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }
}
