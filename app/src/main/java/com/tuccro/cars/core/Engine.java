package com.tuccro.cars.core;

public class Engine {

    private int id;
    private int id_model;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_model() {
        return id_model;
    }

    public void setId_model(int id_model) {
        this.id_model = id_model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine(int id, int id_model, String name) {

        this.id = id;
        this.id_model = id_model;
        this.name = name;
    }
}
