package com.rkumar.talebloom.advices;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse () {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse (T data) {
        this();
        this.data = data;
    }

    public ApiResponse (ApiError error) {
        this();
        this.error = error;
    }

}
