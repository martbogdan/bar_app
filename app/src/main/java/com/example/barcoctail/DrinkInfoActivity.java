package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class DrinkInfoActivity extends AppCompatActivity {

    private ImageView drImg;
    private TextView drName;
    private TextView drAlcohol;
    private TextView drGlass;
    private ListView drIngredients;
    private TextView drInstruction;

    private ArrayList<Ingredients> strDrIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_info);

        drImg = (ImageView) findViewById(R.id.drinkImgInfo);
        drName = (TextView) findViewById(R.id.drinkNameInfo);
        drAlcohol = (TextView) findViewById(R.id.drinkAlcoholInfo);
        drGlass = (TextView) findViewById(R.id.drinkGlassInfo);
        drInstruction = (TextView) findViewById(R.id.drinkInstructionInfo);
        drIngredients = (ListView) findViewById(R.id.lvDrinkIngredientsInfoList);

        Intent intent = getIntent();
        drName.setText("Name: " + intent.getStringExtra("name"));
        drAlcohol.setText("Alcoholic: " + intent.getStringExtra("alcohol"));
        drGlass.setText("Glass: " + intent.getStringExtra("glass"));
        drInstruction.setText(intent.getStringExtra("instruction"));
        // getting ArrayList
        Bundle bundle = getIntent().getExtras();
        strDrIngredients = (ArrayList<Ingredients>) bundle.getSerializable("ingredients");
        ArrayList<Ingredients> listToPrint = new ArrayList<>();
        for (int i = 0; i < strDrIngredients.size(); i++) {
            if (!strDrIngredients.get(i).getIngredient().trim().equals("null")) {
                if (strDrIngredients.get(i).getMeasure().equals("null")) {
                    strDrIngredients.get(i).setMeasure("");
                }
                listToPrint.add(strDrIngredients.get(i));
            }
        }
        IngredientsListAdapter adapter = new IngredientsListAdapter(DrinkInfoActivity.this, R.layout.drink_info, listToPrint);
        drIngredients.setAdapter(adapter);
        // load image
        String imgURL = intent.getStringExtra("imgURL");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_grey);
        requestOptions.error(R.drawable.bg_grey);
        Glide.with(DrinkInfoActivity.this)
                .load(imgURL)
                .apply(requestOptions)
                .into(drImg);
    }
}
