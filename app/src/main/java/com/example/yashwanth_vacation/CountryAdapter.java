package com.example.yashwanth_vacation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yashwanth_vacation.databinding.RowMainBinding;
import com.example.yashwanth_vacation.models.Country;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<ViewHolder> {
    ArrayList<Country> countryList;
    private Context context;
    private final String TAG = this.getClass().getCanonicalName();


    public CountryAdapter(Context context, ArrayList<Country> countryList) {
        this.countryList = countryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item

        RowMainBinding binding = RowMainBinding.inflate(LayoutInflater.from(this.context), viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Country country = this.countryList.get(position);
        viewHolder.bind(this.context, country);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "   "+ countryList.size());
        return this.countryList.size();

    }
}
