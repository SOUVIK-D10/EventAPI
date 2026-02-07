package com.proj.api.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int eventId;
    private int studentId;
    private LocalDateTime createdAt;
    private boolean status;
    public Registration(int eventId, int studentId) {
        this.eventId = eventId;
        this.studentId = studentId;
        this.createdAt = LocalDateTime.now();
        this.status=true;
    }
    public int getId() {
        return id;
    }
    public int getEventId() {
        return eventId;
    }
    public Registration setEventId(int eventId) {
        this.eventId = eventId;
        return this;
    }
    public int getStudentDetails() {
        return studentId;
    }
    public Registration setStudentDetails(int studentId) {
        this.studentId = studentId;
        return this;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Registration setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    public boolean whatStatus() {
        return status;
    }
    public Registration setStatus(boolean status) {
        this.status = status;
        return this;
    }

}
