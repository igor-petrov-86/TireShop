package ru.petrov.tireshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.petrov.tireshop.model.Tire;
import ru.petrov.tireshop.service.TireService;

import java.util.Map;

@Controller
public class TireController {

    @Autowired
    private TireService tireService;

    @RequestMapping("/catalog")
    public String catalog (Map<String, Object> map) {
        Tire tire = new Tire();
        map.put("tire", tire);
        map.put("tireList", tireService.getAllTires());
        return "catalog";
    }
}
