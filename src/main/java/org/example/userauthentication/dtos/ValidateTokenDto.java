package org.example.userauthentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateTokenDto {
    String token;
    Long userId;
}
