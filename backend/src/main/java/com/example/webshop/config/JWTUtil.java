package com.example.webshop.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * JWTUtil is a utility class for handling JSON Web Tokens (JWT).
 * It provides methods for generating and validating JWTs.
 * This class is marked as a Spring component, meaning it can be autowired in other Spring components.
 */
@Component
public class JWTUtil {

    /**
     * The secret key for signing the JWT. It is injected from the application properties.
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * Generates a JWT with the given email as a claim.
     * The token is signed with the secret key and has an expiration time of 6 hours.
     *
     * @param email the email to include in the JWT's claims
     * @return the generated JWT
     * @throws IllegalArgumentException if the secret key is not valid
     * @throws JWTCreationException if there is an error while creating the JWT
     */
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {

        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withIssuedAt(new Date())
                .withExpiresAt(this.createExpirationDate())
                .withIssuer("Duck Studios")
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * Validates the given JWT and retrieves the email claim.
     * The token is validated using the secret key.
     *
     * @param token the JWT to validate
     * @return the email claim from the JWT
     * @throws JWTVerificationException if there is an error while verifying the JWT
     */
    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("Duck Studios")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

    /**
     * Creates the expiration date for the JWT.
     * The expiration date is 6 hours from the current time.
     *
     * @return the expiration date
     */
    private Date createExpirationDate(){
        int expirationHours = 6;
        Calendar appendableDate = Calendar.getInstance();
        appendableDate.setTime(new Date());
        appendableDate.add(Calendar.HOUR, expirationHours);
        return appendableDate.getTime();
    }



}
