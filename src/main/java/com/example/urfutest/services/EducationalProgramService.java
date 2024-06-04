package com.example.urfutest.services;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Module;
import com.example.urfutest.repositories.EducationalProgramRepository;
import com.example.urfutest.repositories.ModuleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EducationalProgramService {
    private final EducationalProgramRepository educationalProgramRepository;
    private final ModuleRepository moduleRepository;

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

    public void assign(UUID programId, UUID moduleId) {
        educationalProgramRepository.findById(programId).ifPresent(educationalProgram -> {
            Module m = moduleRepository.findById(moduleId)
                    .orElseThrow(()->new EntityNotFoundException("Not found module with id = " + moduleId));
            educationalProgram.getModules().add(m);
            m.getPrograms().add(educationalProgram);
            educationalProgramRepository.save(educationalProgram);
        });
    }

    public void release(UUID programId, UUID moduleId) {
        educationalProgramRepository.findById(programId).ifPresent(educationalProgram -> {
            Module m = moduleRepository.findById(moduleId)
                    .orElseThrow(()->new EntityNotFoundException("Not found module with id = " + moduleId));
            educationalProgram.getModules().remove(m);
            m.getPrograms().remove(educationalProgram);
            educationalProgramRepository.save(educationalProgram);
        });
    }

    public void remove(UUID id) {
        educationalProgramRepository.deleteById(id);
    }
}
