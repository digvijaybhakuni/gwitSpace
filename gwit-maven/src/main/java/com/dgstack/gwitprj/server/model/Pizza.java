package com.dgstack.gwitprj.server.model;

import java.util.List;

/**
 * Created by digvijayb on 15/01/17.
 */
public class Pizza {


    private Long id;
    private Long version;

    private String name;
    private List<Ingredient> ingredients;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
