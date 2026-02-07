package com.proj.api.Repo;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proj.api.DTO.EventOverviewDTO;
import com.proj.api.Model.Event;
@Repository
public interface EventRepo extends JpaRepository<Event,Integer> {
    @Query("""
    SELECT e FROM Event e
    WHERE
    (:id IS NULL OR e.id = :id)
    AND ((:title IS NULL OR LOWER(e.title) LIKE LOWER(CONCAT('%', :title, '%'))) OR (:description IS NULL OR LOWER(e.description) LIKE LOWER(CONCAT('%', :description, '%'))))
    AND (:clubName IS NULL OR LOWER(e.clubName) LIKE LOWER(CONCAT('%', :clubName, '%')))
    AND (:location IS NULL OR LOWER(e.location) LIKE LOWER(CONCAT('%', :location, '%')))
    AND (:eventDate IS NULL OR e.eventDate = :eventDate)
    AND (:registrationStart IS NULL OR e.registrationStart >= :registrationStart)
    AND (:registrationEnd IS NULL OR e.registrationEnd <= :registrationEnd)
    AND (:isStarted IS NULL OR e.isStarted = :isStarted)
    AND (:seatRemaining IS NULL OR e.seatRemaining >= :seatRemaining)
""")
Page<EventOverviewDTO> searchEvents(
        @Param("id") Integer id,
        @Param("title") String title,
        @Param("description") String description,
        @Param("clubName") String clubName,
        @Param("location") String location,
        @Param("eventDate") LocalDateTime eventDate,
        @Param("registrationStart") LocalDateTime registrationStart,
        @Param("registrationEnd") LocalDateTime registrationEnd,
        @Param("isStarted") Boolean isStarted,
        @Param("seatRemaining") Integer seatRemaining,
        Pageable pageable
);

}
