package com.example.urfutest.controllers;

import com.example.urfutest.entities.Dict;
import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Head;
import com.example.urfutest.entities.Institute;
import com.example.urfutest.entities.Module;
import com.example.urfutest.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    private final ModuleService moduleService;
    private final HeadService headService;
    private final DictService dictService;

    @GetMapping
    public String fetchAll(Model model) {
        model.addAttribute("educationalPrograms", educationalProgramService.fetchAll());
        return "educationalProgram/allPrograms";
    }

    @GetMapping("/new")
    public String createEducationalProgramPage(@ModelAttribute(value = "educationalProgram") EducationalProgram educationalProgram,
                                               @ModelAttribute(value = "dict") Dict dict,
                                               @ModelAttribute(value = "institute") Institute institute,
                                               @ModelAttribute(value = "head") Head head,
                                               Model model) {
        model.addAttribute("modules", moduleService.fetchAll());
        model.addAttribute("levels", dictService.findAllByParentCode("level"));
        model.addAttribute("standards", dictService.findAllByParentCode("standard"));
        model.addAttribute("institutes", instituteService.fetchAll());
        model.addAttribute("heads", headService.fetchAll());
        return "educationalProgram/new";
    }

    @PostMapping
    public String saveEducationalProgram(@ModelAttribute(value = "educationalProgram") @Valid EducationalProgram educationalProgram, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "educationalProgram/new";
        } else {
            educationalProgramService.save(educationalProgram);
            return "redirect:/educationalPrograms";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPage(Model model,
                           @PathVariable(value = "id") UUID id) {
        model.addAttribute("levels", dictService.findAllByParentCode("level"));
        model.addAttribute("standards", dictService.findAllByParentCode("standard"));
        model.addAttribute("institutes", instituteService.fetchAll());
        model.addAttribute("heads", headService.fetchAll());
        model.addAttribute("educationalProgram", educationalProgramService.findById(id));
        model.addAttribute("modules", moduleService.fetchAll());
        return "educationalProgram/new";
    }

    @PatchMapping("/{id}")
    public String editEducationalProgram(@ModelAttribute(value = "educationalProgram") EducationalProgram educationalProgram) {
        educationalProgramService.save(educationalProgram);
        return "redirect:/educationalPrograms";
    }

    @GetMapping("/{id}/modules")
    public String educationalProgramModules(@PathVariable(value = "id") UUID id,
                                            @ModelAttribute(value = "program") EducationalProgram program,
                                            @ModelAttribute(value = "module") Module module,
                                            Model model) {
        model.addAttribute("modules", educationalProgramService.findModulesByProgramId(id));
        model.addAttribute("freeModules", moduleService.findAllByProgramId(id));
        return "educationalProgram/programModules";
    }

    @DeleteMapping("/{id}/modules")
    public String release(@PathVariable(value = "id") UUID programId,
                          @RequestParam(value = "moduleId") UUID moduleId) {
        educationalProgramService.release(programId, moduleId);
        return "redirect:/educationalPrograms/" + programId + "/modules";
    }

    @PatchMapping("/{id}/modules")
    public String assign(@PathVariable(value = "id") UUID programId,
                         @RequestParam(value = "moduleId") UUID moduleId) {

        educationalProgramService.assign(programId, moduleId);
        return "redirect:/educationalPrograms/" + programId + "/modules";
    }

    @DeleteMapping("/{id}")
    public String deleteEducationalProgram(@PathVariable(value = "id") UUID id) {
        educationalProgramService.remove(id);
        return "redirect:/educationalPrograms";
    }
}
