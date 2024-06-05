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

/**
 * Контроллер отвечающий за запросы,
 * которые взаимодействуют с модулями
 * @author Shevlyakov D.P.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/modules")
public class ModuleController {
    private final ModuleService moduleService;
    /**
     * Метод, который выводит страницу со всеми модулями
     */
    @GetMapping
    public String fetchAll(Model model) {
        model.addAttribute("modules", moduleService.fetchAll());
        return "module/modules";
    }

    /**
     * Метод, который выводит страницу для создания нового модуля
     */
    @GetMapping("/new")
    public String createModulePage(@ModelAttribute(value = "module") Module module) {
        return "module/new";
    }

    /**
     * Метод, который сохраняет данные модуля
     */
    @PostMapping
    public String saveModule(@ModelAttribute(value = "module") @Valid Module module, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "module/new";
        }
        moduleService.save(module);
        return "redirect:/modules";
    }

    /**
     * Метод, который выводит страницу для изменения существующего модуля
     */
    @GetMapping("/{id}/edit")
    public String editModulePage(@PathVariable(value = "id") UUID id, Model model) {
        model.addAttribute("module", moduleService.findById(id));
        return "module/new";
    }

    /**
     * Метод, удаления модуля
     */
    @DeleteMapping("/{id}")
    public String deleteModule(@PathVariable(value = "id") UUID id) {
        moduleService.remove(id);
        return "redirect:/modules";
    }
}

