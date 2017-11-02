package ru.petrov.tireshop.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.petrov.tireshop.dao.TireDao;
import ru.petrov.tireshop.model.Tire;

import java.util.List;

@Repository
public class TireDaoImpl implements TireDao {

    @Autowired
    private SessionFactory session;

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
        return session.getCurrentSession().createQuery("from Tire").list();
    }

    @Override
    public List getTiresByParams(int width, int percentHeight, int radius, String brand, Boolean isWinter) {
        StringBuilder cond = new StringBuilder("");
        if (width != 0){ cond.append("width = "); cond.append(width); }
        if (percentHeight != 0){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("percentHeight = "); cond.append(percentHeight);
        }
        if (radius != 0){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("radius = "); cond.append(radius);
        }
        if (brand != null && !brand.isEmpty()){
            if (cond.length() > 0) cond.append(" and ");
            cond.append("brand = '"); cond.append(brand); cond.append("'");
        }
        if (isWinter != null) {
            if (cond.length() > 0) cond.append(" and ");
            cond.append("isWinter = ");  cond.append(isWinter ? 1 : 0);
        }
        if (cond.length() > 0) { cond.insert(0, " where "); }

        return session.getCurrentSession().createQuery("from Tire "+ cond.toString()).list();
    }
}
