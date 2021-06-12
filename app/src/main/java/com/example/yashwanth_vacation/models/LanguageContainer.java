package com.example.yashwanth_vacation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LanguageContainer {
    private @SerializedName("languages") ArrayList<Language> languageList;

    public ArrayList<Language> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<Language> languageList) {
        this.languageList = languageList;
    }
}
