package org.example.userauthentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Transactional
    public User registerUser(User user) {
        return user;
    }

    public String loginUser(String username, String password) {
        return null;
    }
}
