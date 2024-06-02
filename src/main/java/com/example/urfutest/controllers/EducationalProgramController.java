package com.example.urfutest.controllers;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.services.EducationalProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educationalPrograms")
public class EducationalProgramController {
    private final EducationalProgramService educationalProgramService;

    @GetMapping
    public String fetchAll(Model model) {
        model.addAttribute("educationalPrograms", educationalProgramService.fetchAll());
        return "educationalProgram/allPrograms";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable(value = "id")UUID id, Model model) {
        model.addAttribute("educationalProgram", educationalProgramService.findById(id));
        return "educationalProgram/show";
    }

    @GetMapping("/new")
    public String createEducationalProgramPage(@ModelAttribute(value = "educationalProgram")EducationalProgram educationalProgram) {
        return "educationalProgram/new";
    }

    @PostMapping
    public String createEducationalProgram(@ModelAttribute(value = "educationalProgram") EducationalProgram educationalProgram){
        educationalProgramService.save(educationalProgram);
        return "redirect:/educationalPrograms";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable(value = "id") UUID id, Model model) {
        model.addAttribute("educationalProgram", educationalProgramService.findById(id));
        return "educationalProgram/edit";
    }

    @PatchMapping("/{id}")
    public String editEducationalProgram(@ModelAttribute(value = "educationalProgram") EducationalProgram educationalProgram) {
        educationalProgramService.save(educationalProgram);
        return "redirect:/educationalPrograms";
    }

    @GetMapping("/{id}/modules")
    public String educationalProgramModules(@PathVariable(value = "id") UUID id, Model model) {
        model.addAttribute("modules", educationalProgramService.findModulesByProgramId(id));
        return "educationalProgram/modules";
    }

    @DeleteMapping("/{id}")
    public String deleteEducationalProgram(@PathVariable(value = "id") UUID id) {
        educationalProgramService.remove(id);
        return "redirect:/educationalPrograms";
    }
}
