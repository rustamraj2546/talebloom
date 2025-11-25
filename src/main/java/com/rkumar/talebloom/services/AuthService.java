package com.rkumar.talebloom.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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

    public UserDto signup(SignupDto signupDto) {
        Optional<UserEntity> user = userRepository.findByEmail(signupDto.getEmail());

        if(user.isPresent()) {
             throw new RuntimeException("User with email: " + signupDto.getEmail() + " Already exist.");
        }

        UserEntity userToBeCreated = modelMapper.map(signupDto, UserEntity.class);
//        userToBeCreated.setPassword(); // set Encoded password

        UserEntity savedUser = userRepository.save(userToBeCreated);

        return modelMapper.map(savedUser, UserDto.class);
    }
}
