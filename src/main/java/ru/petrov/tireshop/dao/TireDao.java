package ru.petrov.tireshop.dao;

import ru.petrov.tireshop.model.Tire;

import java.util.List;

public interface TireDao {
    public void add(Tire tire);
    public void edit(Tire tire);
    public void delete(int tireID);
    public Tire getTire(int tireID);
    public List getAllTires();
    public List getTiresByParams(int width, int percentHeight, int radius, String brand, boolean isWinter);
}
