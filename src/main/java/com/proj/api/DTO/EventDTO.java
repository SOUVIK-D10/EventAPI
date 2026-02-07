package com.proj.api.DTO;

import java.time.LocalDateTime;

public record EventDTO(
    String title, String description, String clubName, LocalDateTime eventDate,
        LocalDateTime registrationStart, LocalDateTime registrationEnd, String location, int seatRemaining
) {
    
}
