package com.codecool.model;

import java.util.ArrayList;

public class Company {
    private String name;
    private int id;
    private ArrayList<Car> cars;

    public Company (String name) {
        this.name = name;
    }

    public Company (int id, String name) {
        this.name = name;
        this.id = id;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }
}
