package ru.petrov.tireshop.model;

import javax.persistence.*;

@Entity
public class Tire {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO) //for autonumber
    private int id;
    @Column
    private int width;
    @Column
    private int percentHeight;
    @Column
    private int radius;
    @Column
    private String brand;
    @Column
    private boolean isWinter;

    public Tire() {}
    public Tire(int width, int percentHeight, int radius, String brand, boolean isWinter) {
        super();
        this.width = width;
        this.percentHeight = percentHeight;
        this.radius = radius;
        this.brand = brand;
        this.isWinter = isWinter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPercentHeight() {
        return percentHeight;
    }

    public void setPercentHeight(int percentHeight) {
        this.percentHeight = percentHeight;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isWinter() {
        return isWinter;
    }

    public void setIsWinter(boolean isWinter) {
        this.isWinter = isWinter;
    }

}
