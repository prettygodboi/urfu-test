package com.example.urfutest.repositories;

import com.example.urfutest.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {
}
