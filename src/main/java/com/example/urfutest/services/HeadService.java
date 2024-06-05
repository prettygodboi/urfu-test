package com.example.urfutest.services;

import com.example.urfutest.entities.Head;
import com.example.urfutest.repositories.HeadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис ответственного лица
 */
@Service
@RequiredArgsConstructor
public class HeadService {
    private final HeadRepository headRepository;

    public List<Head> fetchAll() {
        return headRepository.findAll();
    }
}
