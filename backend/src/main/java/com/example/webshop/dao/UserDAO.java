package com.example.webshop.dao;

import com.example.webshop.models.CustomUser;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDAO {

    private final UserRepository userRepository;

    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public  List<CustomUser> getUsers(){
        return this.userRepository.findAll();
    }

    public CustomUser getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public CustomUser updateUser(long id, CustomUser customUser) {
        CustomUser user = this.userRepository.findById(id);
        user.setName(customUser.getName());
        user.setEmail(customUser.getEmail());
        user.setRole(customUser.getRole());
        user.setPhone(customUser.getPhone());
        user.setAddress(customUser.getAddress());
        user.setZip(customUser.getZip());
        user.setCity(customUser.getCity());
        user.setCountry(customUser.getCountry());
        return this.userRepository.save(user);
    }

}
