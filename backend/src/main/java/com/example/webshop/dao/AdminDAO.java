package com.example.webshop.dao;

import com.example.webshop.models.CustomUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminDAO {

    private final UserRepository userRepository;

    public AdminDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  List<CustomUser> getUsers(){
        return this.userRepository.findAll();
    }

    public CustomUser getUserById(long id) {
        return this.userRepository.findById(id);
    }

}
