package com.challenge.bci.controller;

import com.challenge.bci.dto.LoginResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.dto.UserResponseDTO;
import com.challenge.bci.services.IUserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<LoginResponseDTO> registerUser(@RequestBody @Valid UserDTO user) {
        // return new ResponseEntity<>(authService.registerUser(user), HttpStatus.OK);
        return ResponseEntity.ok().body(userService.registerUser(user));
    }
    @GetMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(userService.loginUser(token));
    }

}

