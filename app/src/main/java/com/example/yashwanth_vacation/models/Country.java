package com.example.yashwanth_vacation.models;

import com.google.gson.annotations.SerializedName;

public class Country {
    private @SerializedName("name") String name;
    private @SerializedName("alpha2Code") String code;
    private @SerializedName("capital") String capital;

    public Country(String name, String code, String capital) {
        this.name = name;
        this.code = code;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
