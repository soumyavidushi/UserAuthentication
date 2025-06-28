package org.example.userauthentication.controllers;

import org.example.userauthentication.dtos.UserDto;
import org.example.userauthentication.models.User;
import org.example.userauthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDto getUserDetails(@PathVariable Long userId) {
        User user = userService.getUserDetails(userId);
        System.out.println("User "+user.getEmailId());
        return from(user);
    }

    private UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmailId(user.getEmailId());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
