package com.example.barcoctail.utils;

import android.net.Uri;

import com.example.barcoctail.Drink;
import com.example.barcoctail.Ingredients;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class NetUtils {
    private static final String API_BASE_URL = "https://www.thecocktaildb.com";
    private static final String DRINK_FIND = "/api/json/v1/1/search.php";
    private static final String SEARCH_NAME = "s";

    public static URL generateURL(String drinkName) {
        Uri buildUri = Uri.parse(API_BASE_URL + DRINK_FIND)
                .buildUpon()
                .appendQueryParameter(SEARCH_NAME, drinkName)
                .build();
        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromURL(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            return hasInput ? scanner.next() : null;
        } finally {
            connection.disconnect();
        }
    }

    public static ArrayList<Ingredients> getIngredientsList(Drink drink) {
        ArrayList<Ingredients> ingredients = new ArrayList<>();
        ingredients.add(new Ingredients(drink.getIngredient1(), drink.getMeasure1()));
        ingredients.add(new Ingredients(drink.getIngredient2(), drink.getMeasure2()));
        ingredients.add(new Ingredients(drink.getIngredient3(), drink.getMeasure3()));
        ingredients.add(new Ingredients(drink.getIngredient4(), drink.getMeasure4()));
        ingredients.add(new Ingredients(drink.getIngredient5(), drink.getMeasure5()));
        ingredients.add(new Ingredients(drink.getIngredient6(), drink.getMeasure6()));
        ingredients.add(new Ingredients(drink.getIngredient7(), drink.getMeasure7()));
        ingredients.add(new Ingredients(drink.getIngredient8(), drink.getMeasure8()));
        ingredients.add(new Ingredients(drink.getIngredient9(), drink.getMeasure9()));
        ingredients.add(new Ingredients(drink.getIngredient10(), drink.getMeasure10()));
        ingredients.add(new Ingredients(drink.getIngredient11(), drink.getMeasure11()));
        ingredients.add(new Ingredients(drink.getIngredient12(), drink.getMeasure12()));
        ingredients.add(new Ingredients(drink.getIngredient13(), drink.getMeasure13()));
        ingredients.add(new Ingredients(drink.getIngredient14(), drink.getMeasure14()));
        ingredients.add(new Ingredients(drink.getIngredient15(), drink.getMeasure15()));
        return ingredients;
    }
}
