package com.example.yashwanth_vacation.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CurrencyContainer {
    private @SerializedName("currencies") ArrayList<Currency> currencyList;

    public ArrayList<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(ArrayList<Currency> currencyList) {
        this.currencyList = currencyList;
    }
}
