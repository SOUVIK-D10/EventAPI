package com.proj.api.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proj.api.Model.Registration;

public interface RegistrationRepo extends JpaRepository<Registration,Integer> {
    Registration findByStudentId(int studentId);

    Registration findByStudentIdAndEventId(int id, int id2);
}
