package com.example.MAJ.controllers;

import com.example.MAJ.dto.AuthResponseDto;
import com.example.MAJ.dto.LoginRequestDto;
import com.example.MAJ.dto.RegisterRequestDto;
import com.example.MAJ.service.AuthService;
import com.example.MAJ.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    TokenService tokenService;

    @PostMapping(value = "/api/signUp")
    public AuthResponseDto signUp(@RequestBody RegisterRequestDto in) {
        return authService.signUp(in);
    }

    @PostMapping(value = "/api/signIn")
    public AuthResponseDto signIn(@RequestBody LoginRequestDto in) {
        return authService.signIn(in);
    }

    @PostMapping(value = "/api/signOut/{userID}")
    public void signOut(@PathVariable(value = "userID") Long userID) {
        authService.signOut(userID);
    }

}