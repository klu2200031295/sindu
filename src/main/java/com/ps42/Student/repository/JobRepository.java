package com.ps42.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ps42.Student.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> 
{
	

}
