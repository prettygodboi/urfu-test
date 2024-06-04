package com.example.urfutest.controllers;

import com.example.urfutest.entities.Module;
import com.example.urfutest.services.ModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/modules")
public class ModuleController {
    private final ModuleService moduleService;

    @GetMapping
    public String fetchAll(Model model) {
        model.addAttribute("modules", moduleService.fetchAll());
        return "module/modules";
    }

    @GetMapping("/new")
    public String createModulePage(@ModelAttribute(value = "module") Module module) {
        return "module/new";
    }

    @PostMapping
    public String createModule(@ModelAttribute(value = "module")@Valid Module module, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "module/new";
        }
        moduleService.save(module);
        return "redirect:/modules";
    }

    @GetMapping("/{id}/edit")
    public String editModulePage(@PathVariable(value = "id") UUID id, Model model) {
        model.addAttribute("module", moduleService.findById(id));
        return "module/edit";
    }

    @PatchMapping("/{id}")
    public String editModule(@ModelAttribute(value = "module") Module module) {
        moduleService.save(module);
        return "redirect:/modules";
    }

    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable(value = "id") UUID id) {
        moduleService.remove(id);
        return "redirect:/modules";
    }
}

