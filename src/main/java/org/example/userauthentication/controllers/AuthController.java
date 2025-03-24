package org.example.userauthentication.controllers;

import com.example.auth.model.User;
import com.example.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody @Valid User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        return authService.loginUser(username, password);
    }

    @PostMapping("/logout")
    public String logout() {}
}
