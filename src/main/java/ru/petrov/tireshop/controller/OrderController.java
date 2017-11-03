package ru.petrov.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OrderController {

    @RequestMapping("/order")
    public String basket (Map<String, Object> map) {
        return "order";
    }

    @RequestMapping("/take")
    public String takeOrder (HttpSession httpSession) {
        httpSession.removeAttribute("tire_shop_basket");
        return "redirect:/catalog";
    }
}
