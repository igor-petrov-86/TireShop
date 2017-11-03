package ru.petrov.tireshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.petrov.tireshop.dao.TireDao;
import ru.petrov.tireshop.model.Tire;

import javax.servlet.http.HttpSession;
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
        String excludeId = getCookieId();
        if (!excludeId.isEmpty()) {
            whereCond += " where id not in ("+ excludeId +")";
        }
        return session.getCurrentSession().createQuery("from Tire"+ whereCond).list();
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
        String excludeId = getCookieId();
        if (!excludeId.isEmpty()) {
            if (cond.length() > 0) cond.append(" and ");
            cond.append("id not in (").append(excludeId).append(")");
        }
        if (cond.length() > 0) { cond.insert(0, " where "); }

        return session.getCurrentSession().createQuery("from Tire "+ cond.toString()).list();
    }

    @Override
    public List getTiresInBasket() {
        String tireId = getCookieId();
        if (!tireId.isEmpty()) { return session.getCurrentSession().createQuery("from Tire where id in ("+ tireId +")").list(); }
        return null;
    }

    protected String getCookieId () {
        String basketData = (String)httpSession.getAttribute("tire_shop_basket");
        if (basketData != null) basketData = basketData.replace("'", "");
        else basketData = "";
        return basketData;
    }
}
