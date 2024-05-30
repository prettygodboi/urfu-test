package com.example.urfutest.controllers;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.services.EducationalProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educationalPrograms")
public class EducationalProgramController {
    private final EducationalProgramService educationalProgramService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("educationalPrograms", educationalProgramService.fetchAll());
        return "educationalProgram/programs";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id")UUID id, Model model) {
        model.addAttribute("educationalProgram", educationalProgramService.findById(id));
        return "educationalProgram/show";
    }

    @GetMapping("/new")
    public String createProgramPage(@ModelAttribute(value = "educationalProgram")EducationalProgram educationalProgram) {
        return "educationalProgram/new";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model) {
        model.addAttribute("educationalProgram", educationalProgramService.findById(id));
        return "educationalProgram/edit";
    }
}
