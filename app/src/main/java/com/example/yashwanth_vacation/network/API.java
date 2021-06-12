package com.example.yashwanth_vacation.network;

import com.example.yashwanth_vacation.models.Country;
import com.example.yashwanth_vacation.models.CurrencyContainer;
import com.example.yashwanth_vacation.models.LanguageContainer;
import com.example.yashwanth_vacation.models.MyCountry;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface API {
String BASE_URL = "https://restcountries.eu/rest/v2/";

    @GET(".")
    Call<ArrayList<Country>> retrieveCountries();

    @GET
    Call<MyCountry> getMyCountry(@Url String url);

    @GET
    Call<LanguageContainer> getCountryLanguage(@Url String url);

    @GET
    Call<CurrencyContainer> getCountryCurrency(@Url String url);
}
