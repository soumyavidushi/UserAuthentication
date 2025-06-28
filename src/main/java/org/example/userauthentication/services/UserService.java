package org.example.userauthentication.services;

import org.example.userauthentication.models.User;
import org.example.userauthentication.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserDetails(Long userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if(userOptional.isEmpty()) {
            System.out.println("NO USER FOUND");
            return null;
        }

        System.out.println(userOptional.get().getEmailId());
        return userOptional.get();
    }
}
