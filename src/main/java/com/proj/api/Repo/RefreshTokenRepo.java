package com.proj.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.api.Model.RefreshToken;



public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Integer>{
    RefreshToken findByToken(String token);
}
