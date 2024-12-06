package com.ps42.Student.repository;

import com.ps42.Student.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    // Check if username exists
    Register findByUsername(String username);

    @Query("SELECT r FROM Register r WHERE r.username = :username AND r.password = :password")
    Register findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    boolean existsByUsername(String username);
  
}