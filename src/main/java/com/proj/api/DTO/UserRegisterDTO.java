package com.proj.api.DTO;

public record UserRegisterDTO(
    String name,
    String email,
    String password,
    String rollNo
) {
}
