package com.example.yashwanth_vacation.models;

import com.google.gson.annotations.SerializedName;

public class MyCountry {
    private @SerializedName("name") String name;
    private @SerializedName("capital") String capital;
    private @SerializedName("population") String population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
