package com.example.urfutest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Контроллер с единственным методом, который возвращает
 * страницу логина
 * @author Shevlyakov D.P.
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }
}
