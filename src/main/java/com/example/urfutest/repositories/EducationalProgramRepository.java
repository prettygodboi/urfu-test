package com.example.urfutest.repositories;

import com.example.urfutest.entities.EducationalProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EducationalProgramRepository extends JpaRepository<EducationalProgram, UUID> {
}
