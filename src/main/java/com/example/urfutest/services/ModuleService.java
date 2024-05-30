package com.example.urfutest.services;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Module;
import com.example.urfutest.repositories.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public List<Module> fetchAll() {
        return moduleRepository.findAll();
    }

    public Module findById(UUID id) {
        return moduleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void save(Module module) {
        moduleRepository.save(module);
    }

    public void update(UUID id, Module module) {
        module.setId(id);
        moduleRepository.save(module);
    }

    public void remove(UUID id) {
        moduleRepository.deleteById(id);
    }
}
