package com.rkumar.talebloom.advices;

import com.rkumar.talebloom.exceptions.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();

        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ApiResponse<?>> authenticationExceptionHandler(AuthenticationException exception) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage())
                .build();

        return buildErrorResponseEntity(error);
    }

    @ExceptionHandler(JwtException.class)
    ResponseEntity<ApiResponse<?>> jwtExceptionHandler(JwtException exception) {
        ApiError error = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(exception.getMessage())
                .build();

        return buildErrorResponseEntity(error);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }

}
