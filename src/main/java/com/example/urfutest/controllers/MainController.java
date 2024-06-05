package com.example.urfutest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Контроллер с методом, который возвращает главную страницу
 * @author Shevlyakov D.P.
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    @GetMapping
    public String mainPage() {
        return "mainPage";
    }
}
