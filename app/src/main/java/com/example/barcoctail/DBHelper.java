package com.example.barcoctail;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "drinkDb";
    public static final String TABLE_DRINKS = "drinks";

    public static final String KEY_ID = "idDrink";
    public static final String KEY_NAME = "strDrink";
    public static final String KEY_CATEGORY = "strCategory";
    public static final String KEY_ALCOHOL = "strAlcoholic";
    public static final String KEY_GLASS = "strGlass";
    public static final String KEY_INSTRUCTIONS = "strInstructions";
    public static final String KEY_IMG = "strDrinkThumb";
    public static final String KEY_ING1 = "ingredient1";
    public static final String KEY_ING2 = "ingredient2";
    public static final String KEY_ING3 = "ingredient3";
    public static final String KEY_ING4 = "ingredient4";
    public static final String KEY_ING5 = "ingredient5";
    public static final String KEY_ING6 = "ingredient6";
    public static final String KEY_ING7 = "ingredient7";
    public static final String KEY_ING8 = "ingredient8";
    public static final String KEY_ING9 = "ingredient9";
    public static final String KEY_ING10 = "ingredient10";
    public static final String KEY_ING11 = "ingredient11";
    public static final String KEY_ING12 = "ingredient12";
    public static final String KEY_ING13 = "ingredient13";
    public static final String KEY_ING14 = "ingredient14";
    public static final String KEY_ING15 = "ingredient15";
    public static final String KEY_MEASURE1 = "measure1";
    public static final String KEY_MEASURE2 = "measure2";
    public static final String KEY_MEASURE3 = "measure3";
    public static final String KEY_MEASURE4 = "measure4";
    public static final String KEY_MEASURE5 = "measure5";
    public static final String KEY_MEASURE6 = "measure6";
    public static final String KEY_MEASURE7 = "measure7";
    public static final String KEY_MEASURE8 = "measure8";
    public static final String KEY_MEASURE9 = "measure9";
    public static final String KEY_MEASURE10 = "measure10";
    public static final String KEY_MEASURE11 = "measure11";
    public static final String KEY_MEASURE12 = "measure12";
    public static final String KEY_MEASURE13 = "measure13";
    public static final String KEY_MEASURE14 = "measure14";
    public static final String KEY_MEASURE15 = "measure15";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DRINKS + "(" +
                KEY_ID + " integer primary key," +
                KEY_NAME + " text," + KEY_CATEGORY + " text," + KEY_ALCOHOL + " text," +
                KEY_GLASS + " text," + KEY_INSTRUCTIONS + " text," + KEY_IMG + " text," +
                KEY_ING1 + " text," + KEY_ING2 + " text," + KEY_ING3 + " text," +
                KEY_ING4 + " text," + KEY_ING5 + " text," + KEY_ING6 + " text," +
                KEY_ING7 + " text," + KEY_ING8 + " text," + KEY_ING9 + " text," +
                KEY_ING10 + " text," + KEY_ING11 + " text," + KEY_ING12 + " text," +
                KEY_ING13 + " text," + KEY_ING14 + " text," + KEY_ING15 + " text," +
                KEY_MEASURE1 + " text," + KEY_MEASURE2 + " text," + KEY_MEASURE3 + " text," +
                KEY_MEASURE4 + " text," + KEY_MEASURE5 + " text," + KEY_MEASURE6 + " text," +
                KEY_MEASURE7 + " text," + KEY_MEASURE8 + " text," + KEY_MEASURE9 + " text," +
                KEY_MEASURE10 + " text," + KEY_MEASURE11 + " text," + KEY_MEASURE12 + " text," +
                KEY_MEASURE13 + " text," + KEY_MEASURE14 + " text," + KEY_MEASURE15 + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_DRINKS);
            onCreate(db);
    }
}
