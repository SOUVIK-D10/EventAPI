package com.proj.api.Model;

import java.time.LocalDateTime;

import com.proj.api.Standards.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user_dat")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true)
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Column(unique = true)
    @NotNull
    private String rollNo;
    private String role;
    private LocalDateTime createdAt;
    private boolean isAccountActive;
    public User(){}
    public User(String name, String email, String password,String rollNo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
        this.createdAt = LocalDateTime.now();
        this.isAccountActive = true;
        this.rollNo=rollNo;
    }
    
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public User setName(String name) {
        this.name = name;
        return this;
    }
    public String getEmail() {
        return email;
    }
    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public String getPassword() {
        return password;
    }
    public User setPassword(String password) {
        this.password = password;
        return this;
    }
    public String getRole() {
        return role;
    }
    public User setRole(String role) {
        this.role = role;
        return this;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public User setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public boolean isAccountActive() {
        return isAccountActive;
    }
    public User setAccountActive(boolean isAccountActive) {
        this.isAccountActive = isAccountActive;
        return this;
    }
    public User setRollNo(String rollNo) {
        this.rollNo = rollNo;
        return this;
    }
    public String getRollNo() {
        return rollNo;
    }
}
