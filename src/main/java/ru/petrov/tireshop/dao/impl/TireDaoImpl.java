package ru.petrov.tireshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.petrov.tireshop.dao.TireDao;
import ru.petrov.tireshop.model.Tire;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TireDaoImpl implements TireDao {

    @Autowired
    private SessionFactory session;

    @Autowired
    private HttpSession httpSession;

    @Override
    public void add(Tire tire) {
        session.getCurrentSession().save(tire);
    }

    @Override
    public void edit(Tire tire) {
        session.getCurrentSession().update(tire);
    }

    @Override
    public void delete(int tireID) {
        session.getCurrentSession().delete(getTire(tireID));
    }

    @Override
    public Tire getTire(int tireID) {
        return (Tire)session.getCurrentSession().get(Tire.class, tireID);
    }

    @Override
    public List getAllTires() {
        String whereCond = "";
        String excludeId = getCookieId()[0];
        if (!excludeId.isEmpty()) {
            whereCond += " where id not in ("+ excludeId +")";
        }
        return session.getCurrentSession().createQuery("from Tire "+ whereCond).list();
    }

    @Override
    public List getTiresByParams(int width, int percentHeight, int radius, String brand, Boolean isWinter) {
        StringBuilder cond = new StringBuilder("");
        if (width != 0){ cond.append("width = ").append(width); }
        if (percentHeight != 0){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("percentHeight = ").append(percentHeight);
        }
        if (radius != 0){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("radius = ").append(radius);
        }
        if (brand != null && !brand.isEmpty()){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("brand = '").append(brand).append("'");
        }
        if (isWinter != null) {
            if (cond.length() > 0) cond.append(" and ");
            cond.append("isWinter = ").append(isWinter ? 1 : 0);
        }
        String excludeId = getCookieId()[0];
        if (!excludeId.isEmpty()) {
            if (cond.length() > 0) cond.append(" and ");
            cond.append("id not in (").append(excludeId).append(")");
        }
        if (cond.length() > 0) { cond.insert(0, " where "); }

        return session.getCurrentSession().createQuery("from Tire "+ cond.toString()).list();
    }

    @Override
    public List getTiresInBasket() {
        String[] tireData = getCookieId();
        if (!tireData[0].isEmpty()) {
            String[] arrId = tireData[0].split(",");
            String[] arrCnt = tireData[1].split(",");
            List<List> retList = new ArrayList<>(arrId.length);
            for (int i=0; i<arrId.length; i++) {
                List elem = new ArrayList(2);
                elem.add(this.getTire(Integer.parseInt(arrId[i])));
                elem.add(Integer.parseInt(arrCnt[i]));
                retList.add(elem);
            }
            return retList;
        }
        return null;
    }

    protected String[] getCookieId () {
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        StringBuilder tireId = new StringBuilder("");
        StringBuilder tireCnt = new StringBuilder("");
        if (basketData != null) {
            for (String tire: basketData.split(",")) {
                String[] tmp = tire.split("=");
                tireId.append(tireId.length() > 0 ? "," : "").append(tmp[0].replace("'", ""));
                tireCnt.append(tireCnt.length() > 0 ? "," : "").append(Integer.parseInt(tmp[1]));
            }
        }
        return new String[]{tireId.toString(), tireCnt.toString()};
    }
}
