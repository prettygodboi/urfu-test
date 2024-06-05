package com.example.urfutest.services;

import com.example.urfutest.entities.Institute;
import com.example.urfutest.repositories.InstituteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Сервис института
 */
@Service
@RequiredArgsConstructor
public class InstituteService {
    private final InstituteRepository instituteRepository;

    public List<Institute> fetchAll() {
        return instituteRepository.findAll();
    }
}
