package com.example.urfutest.repositories;

import com.example.urfutest.entities.EducationalProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EducationalProgramRepository extends JpaRepository<EducationalProgram, UUID> {
}
