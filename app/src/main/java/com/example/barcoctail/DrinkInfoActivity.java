package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DrinkInfoActivity extends AppCompatActivity {

    private ImageView drImg;
    private TextView drName;
    private TextView drAlcohol;
    private TextView drGlass;
    private ListView drIngredients;
    private TextView drInstruction;

    private String strDrImg;
    private String strDrName;
    private String strDrAlcohol;
    private String strDrGlass;
    private String[] strDrIngredients;
    private String strDrInstruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_info);

        drImg = (ImageView) findViewById(R.id.drinkImgInfo);
        drName = (TextView) findViewById(R.id.drinkNameInfo);
        drAlcohol = (TextView) findViewById(R.id.drinkAlcoholInfo);
        drGlass = (TextView) findViewById(R.id.drinkGlassInfo);
        drInstruction = (TextView) findViewById(R.id.drinkInstructionInfo);

        Intent intent = getIntent();
        drName.setText("Name: " + intent.getStringExtra("name"));
        drAlcohol.setText("Alcoholic: " + intent.getStringExtra("alcohol"));
        drGlass.setText("Glass: " + intent.getStringExtra("glass"));
        drInstruction.setText(intent.getStringExtra("instruction"));
        String imgURL = intent.getStringExtra("imgURL");

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.bg_grey);
        requestOptions.error(R.drawable.bg_grey);
        Glide.with(DrinkInfoActivity.this)
                .load(imgURL)
                .apply(requestOptions)
                .into(drImg);
    }

    public void setStrDrImg(String strDrImg) {
        this.strDrImg = strDrImg;
    }

    public void setStrDrName(String strDrName) {
        this.strDrName = strDrName;
    }

    public void setStrDrAlcohol(String strDrAlcohol) {
        this.strDrAlcohol = strDrAlcohol;
    }

    public void setStrDrGlass(String strDrGlass) {
        this.strDrGlass = strDrGlass;
    }

    public void setStrDrIngredients(String[] strDrIngredients) {
        this.strDrIngredients = strDrIngredients;
    }

    public void setStrDrInstruction(String strDrInstruction) {
        this.strDrInstruction = strDrInstruction;
    }
}
