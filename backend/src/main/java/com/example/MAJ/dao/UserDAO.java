package com.example.MAJ.dao;


import com.example.MAJ.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Long> {
    public User findByEmail(String email);
}
