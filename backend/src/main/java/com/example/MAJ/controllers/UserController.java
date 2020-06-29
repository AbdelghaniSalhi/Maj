package com.example.MAJ.controllers;

import com.example.MAJ.dto.UserRequestDto;
import com.example.MAJ.dto.UserResponseDto;
import com.example.MAJ.exception.ResourceNotFoundException;
import com.example.MAJ.models.User;
import com.example.MAJ.repository.UserRepository;
import com.example.MAJ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @GetMapping(value="/api/users")
    public Iterable<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(value="api/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId)
    {
        return userService.getUserById(userId);
    }


    @PostMapping(value = "/api/users/create")
    public UserResponseDto createUserWithCart(@RequestBody UserRequestDto user)
    {
        return userService.createUser(user);
    }


    @PutMapping(value = "/api/users/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setLogin(userDetails.getLogin());
        user.setEmail(userDetails.getEmail());
        user.setPwd(userDetails.getPwd());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    @DeleteMapping(value = "/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

}
