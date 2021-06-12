package com.example.yashwanth_vacation;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yashwanth_vacation.databinding.RowMainBinding;
import com.example.yashwanth_vacation.models.Country;

public class ViewHolder extends RecyclerView.ViewHolder{
    private RowMainBinding binding;

    public ViewHolder(RowMainBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Context context, Country country) {
        // to associate the UI with your data
        Glide.with(context).load("https://www.countryflags.io/"+country.getCode()+"/flat/64.png") .placeholder(ContextCompat.getDrawable(context, android.R.drawable.ic_media_next))
                .into(binding.ivLogo);
        this.binding.tvCountryName.setText(country.getName());
        this.binding.tvCapitalName.setText(country.getCapital());
    }
}
