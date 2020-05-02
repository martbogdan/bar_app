package com.example.barcoctail.utils;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
}
