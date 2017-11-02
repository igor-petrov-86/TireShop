package ru.petrov.tireshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.petrov.tireshop.dao.TireDao;
import ru.petrov.tireshop.model.Tire;
import ru.petrov.tireshop.service.TireService;

import java.util.List;

@Service
public class TireServiceImpl implements TireService {

    @Autowired
    private TireDao tireDao;

    @Transactional
    public void add(Tire tire) {
        tireDao.add(tire);
    }

    @Transactional
    public void edit(Tire tire) {
        tireDao.edit(tire);
    }

    @Transactional
    public void delete(int tireID) {
        tireDao.delete(tireID);
    }

    @Transactional
    public Tire getTire(int tireID) {
        return tireDao.getTire(tireID);
    }

    @Transactional
    public List getAllTires() {
        return tireDao.getAllTires();
    }

    @Transactional
    public List getTiresByParams(int width, int percentHeight, int radius, String brand, Boolean isWinter) {
        return tireDao.getTiresByParams(width, percentHeight, radius, brand, isWinter);
    }
}
