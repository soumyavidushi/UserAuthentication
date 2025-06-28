package org.example.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthentication.models.Role;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String emailId;
    private Role role;
}
