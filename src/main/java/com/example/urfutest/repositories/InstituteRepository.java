package com.example.urfutest.repositories;

import com.example.urfutest.entities.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InstituteRepository extends JpaRepository<Institute, UUID> {
}
