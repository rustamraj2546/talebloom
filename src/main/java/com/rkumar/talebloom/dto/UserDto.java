package com.rkumar.talebloom.dto;

import com.rkumar.talebloom.entities.type.RoleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
//    private String password;
    private String gender;
    private Integer age;
    private String bio;
    private RoleType role;
}
