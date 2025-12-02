package com.rkumar.talebloom.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(min = 8, message = "Wrong Password")
    private String password;
}
