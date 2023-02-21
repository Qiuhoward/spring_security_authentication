package com.example.auth.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * <userRepo>
 */
public interface UserRepository extends JpaRepository<UserDetail,Integer> {
    Optional<UserDetail>findByEmail(String email);


}
