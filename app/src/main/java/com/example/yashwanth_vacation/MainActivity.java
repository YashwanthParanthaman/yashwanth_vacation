package com.example.yashwanth_vacation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yashwanth_vacation.databinding.ActivityMainBinding;
import com.example.yashwanth_vacation.helpers.LocationHelper;
import com.example.yashwanth_vacation.models.Country;
import com.example.yashwanth_vacation.network.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private ArrayList<Country> countryList = new ArrayList<>();
    private final String TAG = this.getClass().getCanonicalName();
    CountryAdapter adapter;
    private LocationHelper locationHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        this.locationHelper = LocationHelper.getInstance();
        this.locationHelper.checkPermissions(this);
        setContentView(this.binding.getRoot());

        Log.e(TAG, "   "+ this.countryList.size());


        adapter = new CountryAdapter(this,countryList);
        binding.rvCountries.setAdapter(adapter);
        binding.rvCountries.setLayoutManager(new LinearLayoutManager(this));

        this.getCountryList();




    }

    public void getCountryList(){
        Call<ArrayList<Country>> countryContainerCall = RetrofitClient.getInstance().getApi().retrieveCountries();
        try {
            countryContainerCall.enqueue(new Callback<ArrayList<Country>>() {
                @Override
                public void onResponse(Call<ArrayList<Country>> call, Response<ArrayList<Country>> response) {
                    Log.d(TAG, "Maybe got a response");
                    if (response.isSuccessful()){
                        ArrayList<Country> mainResponse = (ArrayList<Country>)response.body();
                        //if the response from JSON is an array
//                        List<CategoryContainer> mainResponse = response.body();
                            countryList.clear();
                            countryList.addAll(mainResponse);
                            adapter.notifyDataSetChanged();

                        Log.e(TAG, "   "+ countryList.get(0).getCode());
                        }else{
                            Log.e(TAG, "onFailure getCategoryList: Exception occurred while getting categories ");
                        }
                    call.cancel();
                    }

                @Override
                public void onFailure(Call<ArrayList<Country>> call, Throwable t) {
                    Log.e(TAG, "onFailure --- getCategoryList: Exception occurred while getting categories ");
                }
            });
        }catch (Exception ex){
            Log.e(TAG, "getCategoryList: Exception occurred while getting categories " + ex.getLocalizedMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.favourite_list:
            Intent intent = new Intent(this,FavouriteListActivity.class);
            startActivity(intent);
            return(true);
        case R.id.my_country:
            Intent intent1 = new Intent(this,MyCountryActivity.class);
            startActivity(intent1);
            return(true);
    }

        return(super.onOptionsItemSelected(item));
    }


}