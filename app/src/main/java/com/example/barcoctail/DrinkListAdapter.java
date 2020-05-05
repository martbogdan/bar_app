package com.example.barcoctail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class DrinkListAdapter extends ArrayAdapter<Drink> {
    private Context context;
    private int resource;

    public DrinkListAdapter(@NonNull Context context, int resource, @NonNull Drink[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String img = getItem(position).getStrDrinkThumb();
        String name = getItem(position).getStrDrink();
        Drink drink = new Drink();
        drink.setStrDrinkThumb(img);
        drink.setStrDrink(name);

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        //TextView tvImg = (TextView) convertView.findViewById(R.id.textImg);
        TextView tvName = (TextView) convertView.findViewById(R.id.drinkName);

        //tvImg.setText(img);
        tvName.setText(name);

        return convertView;
    }
}
//    RequestOptions requestOptions = new RequestOptions();
//        requestOptions.placeholder(R.drawable.logo);
//                requestOptions.error(R.drawable.logo);
//
//
//                Glide.with(convertView).load(img).apply(requestOptions).into(tvImg);