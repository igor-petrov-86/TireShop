package ru.petrov.tireshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.petrov.tireshop.model.Tire;
import ru.petrov.tireshop.service.TireService;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class TireController {

    @Autowired
    private TireService tireService;

    protected Map<String,String> widthList = new LinkedHashMap<String,String>();
    protected Map<String,String> heightList = new LinkedHashMap<String,String>();
    protected Map<String,String> radiusList = new LinkedHashMap<String,String>();
    protected Map<String,String> brandList = new LinkedHashMap<String,String>();
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
    }

    protected void setStaticLists (Map<String, Object> map) {
        map.put("widthList", widthList);
        map.put("heightList", heightList);
        map.put("radiusList", radiusList);
        map.put("brandList", brandList);
    }

    @RequestMapping("/catalog")
    public String catalog (Map<String, Object> map) {
        Tire tire = new Tire();
        map.put("tire", tire);
        map.put("tireList", tireService.getAllTires());
        setStaticLists(map);
        return "catalog";
    }

    @RequestMapping(value="/search", method=RequestMethod.POST)
    public String search (@ModelAttribute Tire tire, BindingResult result, @RequestParam String action, Map<String, Object> map) {
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
        setStaticLists(map);
        return "catalog";
    }
}
