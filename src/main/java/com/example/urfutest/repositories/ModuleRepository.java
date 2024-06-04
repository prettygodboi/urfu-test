package com.example.urfutest.repositories;

import com.example.urfutest.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<Module, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM module WHERE id NOT IN (SELECT module_id FROM educational_program2module WHERE educational_program_id = :id)")
    List<Module> findAllByProgramId(UUID id);
}
