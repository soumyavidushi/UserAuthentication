package org.example.userauthentication.repos;

import org.example.userauthentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findUserByEmailId(String emailId);

    @Override
    Optional<User> findById(Long userId);
}
