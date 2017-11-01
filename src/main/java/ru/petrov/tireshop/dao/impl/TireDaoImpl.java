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
    private SessionFactory sessionFactory;

    @Override
    public void add(Tire tire) {
        sessionFactory.getCurrentSession().save(tire);
    }

    @Override
    public void edit(Tire tire) {
        sessionFactory.getCurrentSession().update(tire);
    }

    @Override
    public void delete(int tireID) {
        sessionFactory.getCurrentSession().delete(getTire(tireID));
    }

    @Override
    public Tire getTire(int tireID) {
        return (Tire)sessionFactory.getCurrentSession().get(Tire.class, tireID);
    }

    @Override
    public List getAllTires() {
        //return sessionFactory.getCurrentSession().createQuery("FROM Tire").list();
        return null;
    }

    @Override
    public List getTiresByParams(int width, int percentHeight, int radius, String brand, boolean isWinter) {
        return null;
    }
}
