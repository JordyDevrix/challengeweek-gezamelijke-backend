package com.example.webshop.dao;

import com.example.webshop.models.CustomUser;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.webshop.services.CredentialValidator;

import java.util.List;

@Component
public class UserDAO {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserDAO(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public  List<CustomUser> getUsers(){
        return this.userRepository.findAll();
    }

    public CustomUser getUserById(long id) {
        CustomUser user = this.userRepository.findById(id);
        if (user != null) {
            return user;
        }
        return null;
    }

    public CustomUser createUser(CustomUser customUser) {
        // check if user already exists
        if (this.userRepository.findByEmail(customUser.getEmail()) != null) {
            return null;
        }

        // check if password is valid
        CredentialValidator validator = new CredentialValidator();
        if (!validator.isValidPassword(customUser.getPassword())) {
            return null;
        }

        String encodedPassword = passwordEncoder.encode(customUser.getPassword());
        customUser.setPassword(encodedPassword);
        this.userRepository.save(customUser);
        return customUser;
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
        this.userRepository.save(user);
        return user;
    }

    public boolean deleteUser(long id) {
        CustomUser user = this.userRepository.findById(id);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }
        return false;
    }

}
