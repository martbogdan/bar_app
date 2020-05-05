package com.example.barcoctail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class IngredientsListAdapter extends ArrayAdapter<Ingredients> {
    private Context context;
    private int resource;

    public IngredientsListAdapter(@NonNull Context context, int resource, @NonNull List<Ingredients> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ingredient = getItem(position).getIngredient();
        String measure = getItem(position).getMeasure();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView tvIngredient = (TextView) convertView.findViewById(R.id.drinkIngredientInfo);
        TextView tvMeasure = (TextView) convertView.findViewById(R.id.drinkMeasureInfo);
        tvIngredient.setText(ingredient);
        tvMeasure.setText(measure);
        return convertView;
    }
}
