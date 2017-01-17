package com.dgstack.gwitprj.server.model;

/**
 * Created by digvijayb on 15/01/17.
 */
public class Ingredient {

    private String name;
    private boolean vegan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }
}
