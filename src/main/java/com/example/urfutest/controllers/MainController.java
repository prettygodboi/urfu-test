package com.example.urfutest.controllers;

import com.example.urfutest.services.EducationalProgramService;
import com.example.urfutest.services.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    private final EducationalProgramService educationalProgramService;
    private final ModuleService moduleService;
}
