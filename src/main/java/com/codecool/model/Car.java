package com.codecool.model;

public class Car {
    private String brand;
    private String model;
    private int id;
    Company company;

    public Car (String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public Car (String brand, String model, Company company) {
        this.brand = brand;
        this.model = model;
        this.company = company;
    }

    public Car (int id, String brand, String model) {
        this.id = id;
        this.brand = brand;
        this.model = model;
    }

    public Car (int id, String brand, String model, Company company) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.company = company;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
