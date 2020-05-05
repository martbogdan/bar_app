package com.example.barcoctail;

import java.io.Serializable;

public class Ingredients implements Serializable {
    private String ingredient;
    private String measure;

    public Ingredients(String ingredient, String measure) {
        this.ingredient = ingredient;
        this.measure = measure;
    }

    public Ingredients() {
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
