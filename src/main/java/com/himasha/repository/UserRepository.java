package com.himasha.repository;

import com.himasha.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    public  User findByEmail(String email);

}

