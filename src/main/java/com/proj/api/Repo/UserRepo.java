package com.proj.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.api.Model.User;



public interface UserRepo extends JpaRepository<User,Integer> {
    User findByRollNo(String rollNo);
    boolean existsByRollNo(String rollNo);
    User findByEmail(String email);
}
