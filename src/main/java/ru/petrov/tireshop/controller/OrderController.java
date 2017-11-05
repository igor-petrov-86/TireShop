package ru.petrov.tireshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.petrov.tireshop.service.TireService;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class OrderController {

    @Autowired
    private TireService tireService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String fromBasket (String[] tireId, String[] tireCnt, HttpSession httpSession, Map<String, Object> map) {
        if (tireId != null && tireCnt != null && tireId.length > 0 && tireId.length == tireCnt.length) {
            StringBuilder newData = new StringBuilder("");
            for (int i=0; i<tireId.length; i++)
                newData.append(newData.length() > 0 ? "," : "").append("'").append(tireId[i]).append("'=").append(tireCnt[i]);
            httpSession.setAttribute("tire_shop_basket", newData.toString());
        }
        else httpSession.removeAttribute("tire_shop_basket");
        map.put("tireList", tireService.getTiresInBasket());
        return "order";
    }

    @RequestMapping("/take")
    public String takeOrder (HttpSession httpSession, Map<String, Object> map) {
        httpSession.removeAttribute("tire_shop_basket");
        map.put("orderId", (int)(Math.random() * 100));
        return "order";
    }
}
