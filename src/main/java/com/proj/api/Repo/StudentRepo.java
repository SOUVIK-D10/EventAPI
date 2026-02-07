package com.proj.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.api.Model.Student;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    Student findByRollNo(String rollNo);
}
