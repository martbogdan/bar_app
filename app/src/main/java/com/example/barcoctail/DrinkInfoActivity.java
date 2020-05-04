package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DrinkInfoActivity extends AppCompatActivity {

    private ImageView drImg;
    private TextView drName;
    private TextView drAlcohol;
    private TextView drGlass;
    private ListView drIngredients;
    private TextView drInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_info);

        drImg = (ImageView) findViewById(R.id.drinkImgInfo);
        drName = (TextView) findViewById(R.id.drinkNameInfo);
        drAlcohol = (TextView) findViewById(R.id.drinkAlcoholInfo);
        drGlass = (TextView) findViewById(R.id.drinkGlassInfo);
        drInstruction = (TextView) findViewById(R.id.drinkInstructionInfo);
    }
}
