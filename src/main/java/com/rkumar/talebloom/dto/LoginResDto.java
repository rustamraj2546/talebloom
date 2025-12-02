package com.rkumar.talebloom.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {
    private Long id;
    private String accessToken;
    private String refreshToken;
}
