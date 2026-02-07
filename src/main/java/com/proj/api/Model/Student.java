package com.proj.api.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String rollNo;
    public Student( String name, String email, String rollNo) {
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getRollNo() {
        return rollNo;
    }
    public Student setName(String name) {
        this.name = name;
        return this;
    }
    public Student setEmail(String email) {
        this.email = email;
        return this;
    }
    public Student setRollNo(String rollNo) {
        this.rollNo = rollNo;
        return this;
    }
}
