package com.example.barcoctail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DrinkListAdapter extends ArrayAdapter<Drink> {
    private Context context;
    private int resource;

    public DrinkListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Drink> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String imgURL = getItem(position).getStrDrinkThumb();
        String name = getItem(position).getStrDrink();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.textImg);
        TextView tvName = (TextView) convertView.findViewById(R.id.drinkName);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_grey);
        requestOptions.error(R.drawable.bg_grey);
        Glide.with(context)
                .load(imgURL)
                .apply(requestOptions)
                .into(imageView);
        tvName.setText(name);
        return convertView;
    }
}