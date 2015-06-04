package com.tuccro.cars.core;

public class Model extends Item {

    private int id_brand;
    private int startYear;
    private int endYear;

    public int getId_brand() {
        return id_brand;
    }

    public void setId_brand(int id_brand) {
        this.id_brand = id_brand;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public Model(int id, int id_brand, String name, int startYear) {

        this.id = id;
        this.name = name;
        this.id_brand = id_brand;
        this.startYear = startYear;
    }

    public Model(int id, int id_brand, String name, int startYear, int endYear) {

        this.id = id;
        this.name = name;
        this.id_brand = id_brand;
        this.startYear = startYear;
        this.endYear = endYear;
    }
}
