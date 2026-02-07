package com.proj.api.DTO;

public record RegisterDTO(
    String name,
    String email,
    String rollNo,
    int eventId
) {
    
}
