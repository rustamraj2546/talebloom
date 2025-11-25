package com.rkumar.talebloom.dto;

import com.rkumar.talebloom.entities.type.RoleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserProfileDto {
    private Long id;
    private String fullName;
    private String email;
    private String gender;
    private Integer age;
    private String bio;
    private String profilePic;
    private RoleType role;
}
