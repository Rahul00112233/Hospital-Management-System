package com.example.hospitalmsystem.repository;


import com.example.hospitalmsystem.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserAccount,Long> {

    Optional<UserAccount> findByUsername(String username);
    boolean existsByUsername(String username);
}
