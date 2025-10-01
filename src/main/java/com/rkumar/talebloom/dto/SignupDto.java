package com.rkumar.talebloom.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private Long id;

    private String fullName;

    private String email;

    private String password;
    private String confirmPassword;
    private String gender;
    private Integer age;
}
