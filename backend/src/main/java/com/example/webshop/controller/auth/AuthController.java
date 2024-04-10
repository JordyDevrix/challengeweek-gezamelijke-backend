package com.example.webshop.controller.auth;

import com.example.webshop.config.JWTUtil;
import com.example.webshop.dao.UserRepository;
import com.example.webshop.dto.AuthenticationDTO;
import com.example.webshop.dto.LoginResponse;
import com.example.webshop.models.CustomUser;
import com.example.webshop.services.CredentialValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = {"http://s1149771.student.inf-hsleiden.nl:19771", "http://webshop.rickballer.com", "http://localhost:4200"})
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private CredentialValidator validator;

    public AuthController(UserRepository userRepository, JWTUtil jwtUtil, AuthenticationManager authManager,
                          PasswordEncoder passwordEncoder, CredentialValidator validator) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.validator = validator;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody CustomUser customUser) {

        if (!validator.isValidEmail(customUser.getEmail())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No valid email provided"
            );
        }

        if (!validator.isValidPassword(customUser.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "No valid password provided"
            );
        }

        // no properties should be null in customUser
        if (StringUtils.isEmpty(customUser.getName()) || StringUtils.isEmpty(customUser.getPhone()) ||
                StringUtils.isEmpty(customUser.getAddress()) || StringUtils.isEmpty(customUser.getZip()) || StringUtils.isEmpty(customUser.getCity()) ||
                StringUtils.isEmpty(customUser.getEmail()) || StringUtils.isEmpty(customUser.getPassword()) || StringUtils.isEmpty(customUser.getCountry())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "One or more properties are null or empty"
            );
        }

        if (userRepository.findByEmail(customUser.getEmail()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User already exists"
            );
        }

        customUser.setRole("ROLE_USER");

        String encodedPassword = passwordEncoder.encode(customUser.getPassword());
        customUser.setPassword(encodedPassword);
        userRepository.save(customUser);

        String token = jwtUtil.generateToken(customUser.getEmail());
        LoginResponse loginResponse = new LoginResponse(customUser.getEmail(), token);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticationDTO body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.email, body.password);

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.email);

            CustomUser customUser = userRepository.findByEmail(body.email);
            LoginResponse loginResponse = new LoginResponse(customUser.getEmail(), token);


            return ResponseEntity.ok(loginResponse);

        } catch (AuthenticationException authExc) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "No valid credentials"
            );
        }
    }

}
