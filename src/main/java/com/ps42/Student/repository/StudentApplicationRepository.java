package com.ps42.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps42.Student.model.StudentApplication;

public interface StudentApplicationRepository extends  JpaRepository<StudentApplication, Long> {

}
