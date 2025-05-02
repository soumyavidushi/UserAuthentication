package org.example.userauthentication.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthentication.Exceptions.IncorrectPasswordException;
import org.example.userauthentication.Exceptions.UserAlreadyExistsExcpetion;
import org.example.userauthentication.Exceptions.UserNotFoundException;
import org.example.userauthentication.models.User;
import org.example.userauthentication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private SecretKey secretKey;

    @Override
    public User signup(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmailId(email);
        if(userOptional.isPresent()) {
            throw new UserAlreadyExistsExcpetion("User already exists. Please try login.");
        }
        User user = new User();
        user.setEmailId(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public Pair<User,String> login(String email, String password) {
        Optional<User> userOptional = userRepo.findUserByEmailId(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found. Please signup first.");
        }
        String storedPassword = userOptional.get().getPassword();

        if(!bCryptPasswordEncoder.matches(password, storedPassword)) {
            throw new IncorrectPasswordException("Please pass correct password, otherwise reset your password.");
        }
       //  return userOptional.get();

        // Generate Token
      /*  String message = "{\n" +
                "   \"email\": \"anurag@gmail.com\",\n" +
                "   \"roles\": [\n" +
                "      \"instructor\",\n" +
                "      \"buddy\"\n" +
                "   ],\n" +
                "   \"expirationDate\": \"2ndApril2025\"\n" + "}";
        byte[] content = message.getBytes(StandardCharsets.UTF_8); */

        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userOptional.get().getId());
        payload.put("emailId", userOptional.get().getEmailId());
        Long currentTime = System.currentTimeMillis();
        payload.put("iat", currentTime);
        payload.put("exp", currentTime + 24 * 60 * 60 * 1000);
        payload.put("issuer", "Scaler");

      //  MacAlgorithm algorithm = Jwts.SIG.HS256;
      //  SecretKey secretKey = algorithm.key().build();
        String token = Jwts.builder().claims(payload).signWith(secretKey).compact();
      return new Pair<User, String>(userOptional.get(), token);
    }

    public Boolean validateToken(String token, Long userId) {
        try {
            JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
            Claims claims = jwtParser.parseSignedClaims(token).getPayload();

            String newToken = Jwts.builder().claims(claims).signWith(secretKey).compact();
            if (!token.equals(newToken)) {
                System.out.println(newToken);
                System.out.println(token);
                System.out.println("Invalid Token");
                throw new RuntimeException("Invalid Token");
            }

            Long expiry = (Long)claims.get("exp");
            Long currentTime = System.currentTimeMillis();
            if(currentTime > expiry) {
                System.out.println("Token has expired");
                throw new RuntimeException("Token has expired");
            }

            return true;
        }catch (Exception exception) {
            throw exception;
        }
    }
}
