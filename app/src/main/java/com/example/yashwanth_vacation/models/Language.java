package com.example.yashwanth_vacation.models;

import com.google.gson.annotations.SerializedName;

public class Language {
    private @SerializedName("name") String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
