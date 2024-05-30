package com.example.urfutest.services;

import com.example.urfutest.entities.Module;
import com.example.urfutest.repositories.ModuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public List<Module> fetchAll() {
        return moduleRepository.findAll();
    }
}
