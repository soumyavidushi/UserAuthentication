package org.example.userauthentication.services;


import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthentication.models.User;

public interface IAuthService {
    User signup(String email, String password);

    Pair<User, String> login(String email, String password);

    Boolean validateToken(String token, Long userId);
}
