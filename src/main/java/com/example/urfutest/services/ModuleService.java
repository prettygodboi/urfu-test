package com.example.urfutest.services;

import com.example.urfutest.entities.Module;
import com.example.urfutest.repositories.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис модуля
 */
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

    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    public List<Module> findAllByProgramId(UUID id) {
        return moduleRepository.findAllByProgramId(id);
    }

    public void remove(UUID id) {
        moduleRepository.deleteById(id);
    }
}
