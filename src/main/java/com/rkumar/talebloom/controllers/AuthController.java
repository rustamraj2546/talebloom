package com.rkumar.talebloom.controllers;

import com.rkumar.talebloom.dto.LoginDto;
import com.rkumar.talebloom.dto.LoginResDto;
import com.rkumar.talebloom.dto.SignupDto;
import com.rkumar.talebloom.dto.UserDto;
import com.rkumar.talebloom.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signup(@Valid @RequestBody SignupDto signupDto) {
        UserDto createdUser = authService.signup(signupDto);

        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        LoginResDto loginResponse = authService.login(loginDto);

        // Setting cookies for security purpose
        Cookie cookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv)); // used only in production bz this is HTTPS protocol
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh")
    ResponseEntity<LoginResDto> refresh(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        LoginResDto loginResponseDto = authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> refreshTokenCookie = Optional.empty();

        if (request.getCookies() != null) {
            refreshTokenCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> "refreshToken".equals(cookie.getName()))
                    .findFirst();
        }

        if (refreshTokenCookie.isPresent()) {
            Cookie cookie = refreshTokenCookie.get();
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        return ResponseEntity.ok("Logout successful");
    }

}
