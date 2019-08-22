package com.example.recalltracker.Models;

public class VehicleItem {

    private String make;
    private String model;
    private String year;
    private String carVIN;
    private String fullName;

    public VehicleItem(String make, String model, String year, String carVIN) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.carVIN = carVIN;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCarVIN() {
        return carVIN;
    }

    public void setCarVIN(String carVIN) {
        this.carVIN = carVIN;
    }

    public String getFullName() {
        fullName = year + " "  + make + " " + model;
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
