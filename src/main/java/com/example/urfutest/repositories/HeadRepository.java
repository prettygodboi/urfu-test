package com.example.urfutest.repositories;

import com.example.urfutest.entities.Head;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeadRepository extends JpaRepository<Head, UUID> {
}
