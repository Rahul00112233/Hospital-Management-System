package com.example.hospitalmsystem.dto;

import com.example.hospitalmsystem.model.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class RegisterDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private Set<Role> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}


