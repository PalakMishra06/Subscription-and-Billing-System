package com.subscription.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.subscription.billing.entity.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Check if email already exists
    boolean existsByEmail(String email);

    // Optional: find user by email (useful for login)
    Optional<User> findByEmail(String email);
}
