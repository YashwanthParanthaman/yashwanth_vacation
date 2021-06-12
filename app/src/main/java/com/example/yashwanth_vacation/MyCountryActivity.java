package com.example.yashwanth_vacation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.yashwanth_vacation.databinding.ActivityMyCountryBinding;
import com.example.yashwanth_vacation.helpers.LocationHelper;
import com.example.yashwanth_vacation.models.Country;
import com.example.yashwanth_vacation.models.CurrencyContainer;
import com.example.yashwanth_vacation.models.LanguageContainer;
import com.example.yashwanth_vacation.models.MyCountry;
import com.example.yashwanth_vacation.network.RetrofitClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyCountryActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getCanonicalName();

    ActivityMyCountryBinding binding;
    private LocationHelper locationHelper;
    private Location lastLocation;
    private LocationCallback locationCallback;
    private String countryCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_country);
        this.binding = ActivityMyCountryBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());
        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this);
        if (this.locationHelper.locationPermissionGranted) {
            Log.d(TAG, "onCreate: Location Permission Granted");
            this.initiateLocationListener();

            this.locationHelper.getLastLocation(this).observe(this, new Observer<Location>() {
                @Override
                public void onChanged(Location location) {
                    if (location != null) {
                        lastLocation = location;
                        String obtainedCode = locationHelper.getCountryCode(getApplicationContext(), lastLocation);
                        getMyCountry(obtainedCode);
                        Log.e(TAG, "onCreate: Last Location obtained " + lastLocation.toString());
                    }
                }
            });

        }

    }

    public void getMyCountry(String countryCode){
    Call<MyCountry> myCountryCall = RetrofitClient.getInstance().getApi().getMyCountry("./alpha/"+ countryCode);
        try {
            Log.e(TAG, " Country Called ");

            myCountryCall.enqueue(new Callback<MyCountry>() {
                @Override
                public void onResponse(Call<MyCountry> call, Response<MyCountry> response) {
                    Log.d(TAG, "Maybe got a response");
                    if (response.isSuccessful()){
                        MyCountry mainResponse = (MyCountry)response.body();
                        binding.tvCountryName.setText(mainResponse.getName());
                        binding.tvCapitalName.setText(mainResponse.getCapital());
                        binding.tvPopulation.setText(mainResponse.getPopulation());
                        Glide.with(getApplicationContext()).load("https://www.countryflags.io/"+countryCode+"/flat/64.png") .placeholder(ContextCompat.getDrawable(getApplicationContext(), android.R.drawable.ic_media_next))
                                .into(binding.ivLogo);
                        Log.e(TAG, "   "+ mainResponse.getName());

                    }else{
                        Log.e(TAG, "onFailure getCategoryList: Exception occurred while getting categories ");
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<MyCountry> call, Throwable t) {
                    Log.e(TAG, "onFailure --- getCategoryList: Exception occurred while getting categories ");
                }
            });
        }catch (Exception ex){
            Log.e(TAG, "getCategoryList: Exception occurred while getting categories " + ex.getLocalizedMessage());
        }

        Call<LanguageContainer> languageContainerCall = RetrofitClient.getInstance().getApi().getCountryLanguage("./alpha/"+ countryCode);
        try {
            Log.e(TAG, " Country Called ");

            languageContainerCall.enqueue(new Callback<LanguageContainer>() {
                @Override
                public void onResponse(Call<LanguageContainer> call, Response<LanguageContainer> response) {
                    Log.d(TAG, "Maybe got a response");
                    if (response.isSuccessful()){
                        LanguageContainer mainResponse = (LanguageContainer) response.body();
                        binding.tvLanguage.setText(mainResponse.getLanguageList().get(0).getName());
                    }else{
                        Log.e(TAG, "onFailure getCategoryList: Exception occurred while getting categories ");
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<LanguageContainer> call, Throwable t) {
                    Log.e(TAG, "onFailure --- getCategoryList: Exception occurred while getting categories ");
                }
            });
        }catch (Exception ex){
            Log.e(TAG, "getCategoryList: Exception occurred while getting categories " + ex.getLocalizedMessage());
        }

        Call<CurrencyContainer> currencyContainerCall = RetrofitClient.getInstance().getApi().getCountryCurrency("./alpha/"+ countryCode);
        try {
            Log.e(TAG, " Country Called ");

            currencyContainerCall.enqueue(new Callback<CurrencyContainer>() {
                @Override
                public void onResponse(Call<CurrencyContainer> call, Response<CurrencyContainer> response) {
                    Log.d(TAG, "Maybe got a response");
                    if (response.isSuccessful()){
                        CurrencyContainer mainResponse = (CurrencyContainer) response.body();
                        binding.tvCurrency.setText(mainResponse.getCurrencyList().get(0).getName());
                    }else{
                        Log.e(TAG, "onFailure getCategoryList: Exception occurred while getting categories ");
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<CurrencyContainer> call, Throwable t) {
                    Log.e(TAG, "onFailure --- getCategoryList: Exception occurred while getting categories ");
                }
            });
        }catch (Exception ex){
            Log.e(TAG, "getCategoryList: Exception occurred while getting categories " + ex.getLocalizedMessage());
        }
    }





    private void initiateLocationListener() {
        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

                for (Location loc : locationResult.getLocations()) {
                    lastLocation = loc;
                    countryCode = locationHelper.getCountryCode(getApplicationContext(), loc);
                    Log.e(TAG, "onLocationResult: update location " + countryCode);
                }
            }
        };
        this.locationHelper.requestLocationUpdates(this, this.locationCallback);
    }
}
