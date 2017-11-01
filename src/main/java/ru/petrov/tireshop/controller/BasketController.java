package ru.petrov.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasketController {

    @RequestMapping("/basket")
    public String basket (Model model) {
        model.addAttribute("wareList", "Список товаров пуст!");
        return "basket";
    }
}
