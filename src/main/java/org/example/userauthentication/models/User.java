package org.example.userauthentication.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class User {
    @Entity
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String username;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        private boolean enabled = true;

        // Getters and Setters
    }
}
