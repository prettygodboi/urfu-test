package com.example.urfutest.controllers;

import com.example.urfutest.entities.Dict;
import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Head;
import com.example.urfutest.entities.Institute;
import com.example.urfutest.services.EducationalProgramService;
import com.example.urfutest.services.InstituteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/educationalPrograms")
public class EducationalProgramController {
    private final EducationalProgramService educationalProgramService;
    private final InstituteService instituteService;

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
    public String createEducationalProgram(Model model,
                                           @ModelAttribute(value = "educationalProgram") @Valid EducationalProgram educationalProgram,
//                                           @ModelAttribute(value = "dict")Dict dict,
                                           @ModelAttribute(value = "institute")Institute institute,
                                           @ModelAttribute(value = "head")Head head){

        model.addAttribute("institutes", instituteService.fetchAll());
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
