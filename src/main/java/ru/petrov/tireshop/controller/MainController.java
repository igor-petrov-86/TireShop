package ru.petrov.tireshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping
    public String mainPage (HttpSession httpSession, Map<String, Object> map) {
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        if (basketData == null || basketData.isEmpty()) map.put("basketCapacity", " пуста");
        else map.put("basketCapacity", " ("+ basketData.split(",").length +")");
        return "main";
    }
}
