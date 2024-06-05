package com.example.urfutest.services;

import com.example.urfutest.entities.Dict;
import com.example.urfutest.repositories.DictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис словаря
 */
@Service
@RequiredArgsConstructor
public class DictService {
    private final DictRepository dictRepository;

    public List<Dict> findAllByParentCode(String bcode) {
        return dictRepository.findAllByParentCode(bcode);
    }
}
