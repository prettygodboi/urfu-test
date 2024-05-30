package com.example.urfutest.services;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.repositories.EducationalProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void save(EducationalProgram educationalProgram) {
        educationalProgramRepository.save(educationalProgram);
    }

    public void update(UUID id, EducationalProgram educationalProgram) {
        educationalProgram.setId(id);
        educationalProgramRepository.save(educationalProgram);
    }

    public void remove(UUID id) {
        educationalProgramRepository.deleteById(id);
    }
}
