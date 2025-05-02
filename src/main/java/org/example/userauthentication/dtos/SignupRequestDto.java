package org.example.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String emailId;
    private String password;
}
