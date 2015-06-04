package com.tuccro.cars.core;

public class Engine extends Item{
    
    private int id_model;

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public Engine(int id, int id_model, String name) {

        this.id = id;
        this.name = name;
        this.id_model = id_model;
    }
}
