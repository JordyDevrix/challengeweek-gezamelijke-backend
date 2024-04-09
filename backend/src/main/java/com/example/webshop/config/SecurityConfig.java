package com.example.webshop.config;

import com.example.webshop.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig class is responsible for the security configuration of the application.
 * It defines the security filter chain, password encoder and authentication manager beans.
 * It also configures the HTTP security details like CSRF, CORS, session management, user details service,
 * authentication filter and authorization of HTTP requests.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JWTFilter filter;
    private final UserService userService;

    /**
     * Constructor for SecurityConfig.
     * @param filter JWTFilter instance for JWT token processing.
     * @param userService UserService instance for user related operations.
     */
    public SecurityConfig(JWTFilter filter, UserService userService) {
        this.filter = filter;
        this.userService = userService;
    }

    /**
     * Defines the security filter chain bean.
     * @param http HttpSecurity instance for configuring web based security for specific http requests.
     * @return SecurityFilterChain instance.
     * @throws Exception if any error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(userService)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/pub/**").permitAll()
                        .requestMatchers("/error").anonymous()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .build();
    }

    /**
     * Defines the password encoder bean.
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the authentication manager bean.
     * @param authenticationConfiguration AuthenticationConfiguration instance for authentication configuration.
     * @return AuthenticationManager instance.
     * @throws Exception if any error occurs during the configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}



