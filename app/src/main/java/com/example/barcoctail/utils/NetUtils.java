package com.example.barcoctail.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

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
}
