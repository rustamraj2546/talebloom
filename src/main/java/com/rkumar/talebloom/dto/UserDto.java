package com.rkumar.talebloom.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private Integer age;
}
