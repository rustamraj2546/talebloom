package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.dto.SignupDto;
import com.rkumar.talebloom.dto.UserDto;
import com.rkumar.talebloom.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto createdUser = userService.signup(signupDto);

        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("login")
    ResponseEntity<String> longin() {
        return ResponseEntity.ok("Welcome to Tale Bloom");
    }

}
