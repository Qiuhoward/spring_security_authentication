package com.example.auth.entity.user;

import com.example.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * <userRepo>
 */
public interface UserRepository extends JpaRepository<UserDetail,Integer> {

    //查詢所有名單
    List<UserDetail>findAll();

    //查詢帳號
    Optional<UserDetail>findByEmail(String email);

    //查詢職位
    List<UserDetail> findUserDetailByRole(Role role);

}
