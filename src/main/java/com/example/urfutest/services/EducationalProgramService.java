package com.example.urfutest.services;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Module;
import com.example.urfutest.repositories.EducationalProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationalProgramService {
    private final EducationalProgramRepository educationalProgramRepository;

    public List<EducationalProgram> fetchAll() {
        return educationalProgramRepository.findAll();
    }

    public EducationalProgram findById(UUID id) {
        return educationalProgramRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public EducationalProgram save(EducationalProgram educationalProgram) {
        return educationalProgramRepository.save(educationalProgram);
    }

    public List<Module> findModulesByProgramId(UUID id) {
        Optional<EducationalProgram> educationalProgram = educationalProgramRepository.findById(id);
        if (educationalProgram.isPresent()) {
            return educationalProgram.get().getModules();
        } else {
            return Collections.emptyList();
        }
    }

    public void assign(UUID id, Module module) {
        educationalProgramRepository.findById(id).ifPresent(educationalProgram -> {
            educationalProgram.getModules().add(module);
            module.getPrograms().add(educationalProgram);
            educationalProgramRepository.save(educationalProgram);
        });
    }

    public void release(UUID id, Module module) {
        educationalProgramRepository.findById(id).ifPresent(educationalProgram -> {
            educationalProgram.getModules().remove(module);
            module.getPrograms().remove(educationalProgram);
            educationalProgramRepository.save(educationalProgram);
        });
    }

    public void remove(UUID id) {
        educationalProgramRepository.deleteById(id);
    }
}
