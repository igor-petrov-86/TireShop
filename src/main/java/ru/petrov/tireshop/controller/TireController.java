package ru.petrov.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TireController {

    @RequestMapping("/catalog")
    public String catalog (Model model) {
        model.addAttribute("hello", "Каталог товаров");
        return "catalog";
    }
}
