package com.example.hospitalmsystem.service;

import com.example.hospitalmsystem.model.UserAccount;
import com.example.hospitalmsystem.repository.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount u = userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));
        return User.withUsername(u.getUsername())
                .password(u.getPassword())
                .authorities(u.getRole().stream().map(r -> "ROLE_" + r.name()).toArray(String[]::new))
                .build();
    }
}
