package com.example.MAJ.service;


import com.example.MAJ.controllers.UserController;
import com.example.MAJ.dao.UserDAO;
import com.example.MAJ.dto.UserRequestDto;
import com.example.MAJ.dto.UserResponseDto;
import com.example.MAJ.exception.ResourceNotFoundException;
import com.example.MAJ.models.User;
import com.example.MAJ.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    private UserDAO userDAO;

    public UserResponseDto createUser(UserRequestDto in) {
        User user = null;
        if (in.getLogin().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Incorrect value for login");
        } else if (getUserByEmail(in.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "This email is already used");
        } else {
            user = new User();
            user.setRole(in.getRole().toUpperCase());
            user.setEmail(in.getEmail().toLowerCase());
            user.setLogin(in.getLogin());
            user.setPwd(in.getPwd());
            userRepository.save(user);
        }
        return new UserResponseDto(user.getId(), user.getEmail(), user.getLogin());
    }


    public User getUserByEmail(String email) {

        return userDAO.findByEmail(email);
    }


    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long userID, UserRequestDto up) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));

        user.setLogin(up.getLogin());
        user.setEmail(up.getEmail());
        user.setPwd(up.getPwd());
        user.setRole(up.getRole());

        userRepository.save(user);
    }

    public void updateUser(User user) {
        //userRepository.save(user);
    }

    public User getUserById(Long userID) {
        return userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userID", userID));
    }



}