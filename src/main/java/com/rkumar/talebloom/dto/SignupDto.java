package com.rkumar.talebloom.dto;


import com.rkumar.talebloom.entities.type.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private Long id;

    @NotBlank(message = "Full Name can't be NULL")
    @Size(min = 3, message = "Full Name must contain at least 3 characters")
    private String fullName;

    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank(message = "gender field is required")
    private String gender;

    @NotBlank(message = "age field is required")
    private Integer age;

    private String bio;

    @NotBlank(message = "Select your role: 'USER' or 'ADMIN' ")
    private RoleType role;
}
