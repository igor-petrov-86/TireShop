package ru.petrov.tireshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.petrov.tireshop.model.Tire;
import ru.petrov.tireshop.service.TireService;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class TireController {

    @Autowired
    private TireService tireService;

    protected Map<String,String> widthList = new LinkedHashMap<>();
    protected Map<String,String> heightList = new LinkedHashMap<>();
    protected Map<String,String> radiusList = new LinkedHashMap<>();
    protected Map<String,String> brandList = new LinkedHashMap<>();
    {
        widthList.put("235","235");
        widthList.put("245","245");
        widthList.put("255","255");
        widthList.put("265","265");

        heightList.put("60","60");
        heightList.put("65","65");
        heightList.put("70","70");
        heightList.put("75","75");

        radiusList.put("15", "15");
        radiusList.put("16", "16");
        radiusList.put("17", "17");
        radiusList.put("18", "18");

        brandList.put("Continental", "Continental");
        brandList.put("GT Radial", "GT Radial");
        brandList.put("Nokian Hakkapeliitta 8 Suv", "Nokian Hakkapeliitta 8 Suv");
        brandList.put("Hankook", "Hankook");
        brandList.put("Yokohama", "Yokohama");
        brandList.put("Goodyear", "Goodyear");
        brandList.put("BFGoodrich", "BFGoodrich");
        brandList.put("Bridgestone", "Bridgestone");
    }

    protected void setCommonData(HttpSession httpSession, Map<String, Object> map) {
        map.put("widthList", widthList);
        map.put("heightList", heightList);
        map.put("radiusList", radiusList);
        map.put("brandList", brandList);
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        if (basketData == null || basketData.isEmpty()) map.put("basketCapacity", " пуста");
        else map.put("basketCapacity", " ("+ basketData.split(",").length +")");
    }

    @RequestMapping("/catalog")
    public String catalog (HttpSession httpSession, Map<String, Object> map) {
        Tire tire = new Tire();
        map.put("tire", tire);
        map.put("tireList", tireService.getAllTires());
        setCommonData(httpSession, map);
        return "catalog";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search (@ModelAttribute Tire tire, HttpSession httpSession, @RequestParam String action, Map<String, Object> map) {
        switch(action.toLowerCase()) {
            case "найти":
                map.put("tire", tire);
                map.put("tireList", tireService.getTiresByParams(tire.getWidth(), tire.getPercentHeight(),
                                    tire.getRadius(), tire.getBrand(), tire.getIsWinter()));
                break;
            case "сбросить фильтр":
                Tire newTire = new Tire();
                map.put("tire", newTire);
                map.put("tireList", tireService.getAllTires());
                break;
        }
        setCommonData(httpSession, map);
        return "catalog";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String intoBasket (int id, HttpSession httpSession) {
        String tireId = "'"+ id +"'";
        String tireData = tireId +"=4";
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        if (basketData == null) basketData = tireData;
        else if (!basketData.contains(tireId)) basketData += ","+ tireData;
        httpSession.setAttribute("tire_shop_basket", basketData);
        return "redirect:/catalog";
    }
}
