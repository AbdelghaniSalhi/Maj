package com.example.MAJ.service;

import com.example.MAJ.dto.AuthResponseDto;
import com.example.MAJ.dto.LoginRequestDto;
import com.example.MAJ.dto.RegisterRequestDto;
import com.example.MAJ.dto.UserRequestDto;
import com.example.MAJ.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    @Autowired
    com.example.MAJ.service.TokenService tokenService;

    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.MAJ.service.AuthService.class);

    public AuthResponseDto signUp(RegisterRequestDto in) {

        if (in.getPwd() != null && !in.getPwd().isEmpty()) {
            userService.createUser(new UserRequestDto(in.getEmail(), in.getLogin(), in.getPwd(), in.getRole()));
            return signIn(new LoginRequestDto(in.getEmail(), in.getPwd()));
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Password cannot be empty");
        }
    }

    public AuthResponseDto signIn(LoginRequestDto in) {
        User user = userService.getUserByEmail(in.getEmail());
        Long ID= user.getId();
        if (user.getPwd().equals(in.getPwd()) && user.getEmail().equals(in.getEmail())){
            String token = tokenService.createToken(String.valueOf(ID));
            userService.updateUser(user);
            return new AuthResponseDto(token, user.getId());
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid email or password");
        }
    }

    public void signOut(Long userID) {
        User user = userService.getUserById(userID);
        userService.updateUser(user);

    }
}