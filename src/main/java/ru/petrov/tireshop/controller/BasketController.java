package ru.petrov.tireshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.petrov.tireshop.service.TireService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private TireService tireService;

    @RequestMapping("/basket")
    public String basket (Map<String, Object> map) {
        List basketData = tireService.getTiresInBasket();
        if (basketData == null) {
            map.put("emptyBasket", "<font color=\"red\">К сожалению, здесь пока пусто!!!</font>");
            map.put("tireList", null);
        }
        else {
            map.put("emptyBasket", "");
            map.put("tireList", basketData);
        }
        return "basket";
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String removeFromBasket (int id, HttpSession httpSession) {
        String tireId = "'"+ id +"'";
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        if (basketData != null && basketData.contains(tireId)) {
            basketData = basketData.replace(tireId, "").replace(",,", ",");
            if (basketData.startsWith(",")) basketData = basketData.substring(1);
            if (basketData.endsWith(",")) basketData = basketData.substring(0, basketData.length() - 1);
            if (!basketData.isEmpty())
                httpSession.setAttribute("tire_shop_basket", basketData);
            else
                httpSession.removeAttribute("tire_shop_basket");
        }
        return "redirect:/basket";
    }

    @RequestMapping("/clear")
    public String clearBasket (HttpSession httpSession) {
        httpSession.removeAttribute("tire_shop_basket");
        return "redirect:/basket";
    }
}
