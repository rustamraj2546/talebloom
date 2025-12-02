package com.rkumar.talebloom.services;

import java.util.Optional;

import com.rkumar.talebloom.dto.LoginDto;
import com.rkumar.talebloom.dto.LoginResDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rkumar.talebloom.dto.SignupDto;
import com.rkumar.talebloom.dto.UserDto;
import com.rkumar.talebloom.entities.UserEntity;
import com.rkumar.talebloom.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public UserDto signup(SignupDto signupDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signupDto.getEmail());

        if(user.isPresent()) {
             throw new RuntimeException("User with email: " + signupDto.getEmail() + " Already exist.");
        }

        UserEntity userToBeCreated = modelMapper.map(signupDto, UserEntity.class);
        userToBeCreated.setPassword(passwordEncoder.encode(userToBeCreated.getPassword())); // set Encoded password

        UserEntity savedUser = userRepository.save(userToBeCreated);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public LoginResDto login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResDto(user.getId(), accessToken, refreshToken);
    }

    public LoginResDto refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);

        UserEntity user = userService.getUserById(userId);

        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResDto(user.getId(), accessToken, refreshToken);
    }
}
