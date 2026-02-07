package com.proj.api.DTO;

import java.time.LocalDateTime;


public record SearchDTO(
    Integer id,
    String title,
    String description,
    String clubName,
    String location,
    LocalDateTime eventDate,
    LocalDateTime registrationStart,
    LocalDateTime registrationEnd,
    Boolean isStarted,
    Integer seatRemaining
) {
    
}
