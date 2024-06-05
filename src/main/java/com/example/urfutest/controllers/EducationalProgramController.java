package com.example.urfutest.controllers;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.entities.Module;
import com.example.urfutest.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

/**
 * Контроллер отвечающий за запросы, которые
 * взаимодействие с образовательными программами
 * @author Shevlyakov D.P.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/educationalPrograms")
public class EducationalProgramController {
    private final EducationalProgramService educationalProgramService;
    private final InstituteService instituteService;
    private final ModuleService moduleService;
    private final HeadService headService;
    private final DictService dictService;
    /**
     * Метод, который выводит страницу со всеми образовательными программами
     */
    @GetMapping
    public String fetchAll(Model model) {
        model.addAttribute("educationalPrograms", educationalProgramService.fetchAll());
        return "educationalProgram/allPrograms";
    }

    /**
     * Метод, который выводит страницу для создания новой образовательной программы
     */
    @GetMapping("/new")
    public String createEducationalProgramPage(@ModelAttribute(value = "educationalProgram") EducationalProgram educationalProgram,
                                               Model model) {
        model.addAttribute("modules", moduleService.fetchAll());
        model.addAttribute("levels", dictService.findAllByParentCode("level"));
        model.addAttribute("standards", dictService.findAllByParentCode("standard"));
        model.addAttribute("institutes", instituteService.fetchAll());
        model.addAttribute("heads", headService.fetchAll());
        return "educationalProgram/new";
    }

    /**
     * Метод, который сохраняет данные образовательной программы
     */
    @PostMapping
    public String saveEducationalProgram(@ModelAttribute(value = "educationalProgram") @Valid EducationalProgram educationalProgram,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("educationalProgram", educationalProgram);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/educationalPrograms/new";
        }
        educationalProgramService.save(educationalProgram);
        return "redirect:/educationalPrograms";

    }

    /**
     * Метод, который выводит страницу для изменения существующей образовательной программы
     */
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

    /**
     * Метод, который выводит страницу с модулями образовательной программы
     */
    @GetMapping("/{id}/modules")
    public String educationalProgramModules(@PathVariable(value = "id") UUID id,
                                            @ModelAttribute(value = "program") EducationalProgram program,
                                            @ModelAttribute(value = "module") Module module,
                                            Model model) {
        model.addAttribute("modules", educationalProgramService.findModulesByProgramId(id));
        model.addAttribute("freeModules", moduleService.findAllByProgramId(id));
        return "educationalProgram/programModules";
    }

    /**
     * Метод, который убирает связь между образовательной программой и модулем
     */
    @DeleteMapping("/{id}/modules")
    public String release(@PathVariable(value = "id") UUID programId,
                          @RequestParam(value = "moduleId") UUID moduleId) {
        educationalProgramService.release(programId, moduleId);
        return "redirect:/educationalPrograms/" + programId + "/modules";
    }

    /**
     * Метод, который позволяет связать модуль и образовательную программу
     */
    @PatchMapping("/{id}/modules")
    public String assign(@PathVariable(value = "id") UUID programId,
                         @RequestParam(value = "moduleId") UUID moduleId) {
        educationalProgramService.assign(programId, moduleId);
        return "redirect:/educationalPrograms/" + programId + "/modules";
    }

    /**
     * Метод удаления образовательной программы
     */
    @DeleteMapping("/{id}")
    public String deleteEducationalProgram(@PathVariable(value = "id") UUID id) {
        educationalProgramService.remove(id);
        return "redirect:/educationalPrograms";
    }
}
