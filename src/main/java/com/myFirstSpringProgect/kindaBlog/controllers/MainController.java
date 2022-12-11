package com.myFirstSpringProgect.kindaBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    // Обработка определённого URL адреса и вызов шаблона:
    @GetMapping("/") // При переходе по этому URL функция home вызывает соотв. шаблон (get запрос)
    public String home(Model model) {
        model.addAttribute("title", "Home page"); // Параметр, передающийся в шаблон
        return "home"; // Вызов шаблона
    }

    @GetMapping("/about") // При переходе по этому URL функция home вызывает соотв. шаблон
    public String about(Model model) {
        model.addAttribute("title", "Everything about me"); // Параметр, передающийся в шаблон
        return "about"; // Вызов шаблона
    }

}
