package org.example.userauthentication.Exceptions;

public class UserAlreadyExistsExcpetion extends RuntimeException {
    public UserAlreadyExistsExcpetion(String message) {
        super(message);
    }
}
