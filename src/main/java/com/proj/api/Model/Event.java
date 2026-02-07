package com.proj.api.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "event")
public class Event {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String title;
private String description;
private String clubName;
private LocalDateTime eventDate;
private LocalDateTime registrationStart;
private LocalDateTime registrationEnd;
private boolean isStarted;
private String location;
private int seatRemaining;
private int registeredCount;
private LocalDateTime createdAt;
public Event(){}
public Event(String title, String description, String clubName, LocalDateTime eventDate,
        LocalDateTime registrationStart, LocalDateTime registrationEnd, String location, int seatRemaining) {
    this.title = title;
    this.description = description;
    this.clubName = clubName;
    this.eventDate = eventDate;
    this.registrationStart = registrationStart;
    this.registrationEnd = registrationEnd;
    this.location = location;
    this.seatRemaining = seatRemaining;
    this.isStarted = LocalDateTime.now().isAfter(eventDate);
    this.createdAt = LocalDateTime.now();
    this.registeredCount=0;
}
public Event setTitle(String title) {
    this.title = title;
    return this;
}
public Event setDescription(String description) {
    this.description = description;
    return this;
}
public Event setClubName(String clubName) {
    this.clubName = clubName;
    return this;
}
public Event setEventDate(LocalDateTime eventDate) {
    this.eventDate = eventDate;
    return this;
}
public Event setRegistrationStart(LocalDateTime registrationStart) {
    this.registrationStart = registrationStart;
    return this;
}
public Event setRegistrationEnd(LocalDateTime registrationEnd) {
    this.registrationEnd = registrationEnd;
    return this;
}
public Event setStarted(boolean isStarted) {
    this.isStarted = isStarted;
    return this;
}
public Event setLocation(String location) {
    this.location = location;
    return this;
}
public Event setSeatRemaining(int seatRemaining) {
    this.seatRemaining = seatRemaining;
    return this;
}
public Event setRegisteredCount(int registeredCount) {
    this.registeredCount = registeredCount;
    return this;
}
public int getId() {
    return id;
}
public String getTitle() {
    return title;
}
public String getDescription() {
    return description;
}
public String getClubName() {
    return clubName;
}
public LocalDateTime getEventDate() {
    return eventDate;
}
public LocalDateTime getRegistrationStart() {
    return registrationStart;
}
public LocalDateTime getRegistrationEnd() {
    return registrationEnd;
}
public boolean isStarted() {
    return isStarted;
}
public String getLocation() {
    return location;
}
public int getSeatRemaining() {
    return seatRemaining;
}
public int getRegisteredCount() {
    return registeredCount;
}
public LocalDateTime getCreatedAt() {
    return createdAt;
}
}
