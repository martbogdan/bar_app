package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.barcoctail.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class SearchScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText searchField;
    private Button searchButton;
    private TextView result;

    class DrinkQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = NetUtils.getResponseFromURL(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            String[] strDrink = null;
            String[] strCategory = null;
            String[] strAlcoholic = null;
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray drinks = jsonResponse.getJSONArray("drinks");
                JSONObject drink;
                strDrink = new String[drinks.length()];
                strCategory = new String[drinks.length()];
                strAlcoholic = new String[drinks.length()];
                for (int i = 0; i < drinks.length(); i++) {
                    drink = drinks.getJSONObject(i);
                    strDrink[i] = drink.getString("strDrink");
                    strCategory[i] = drink.getString("strCategory");
                    strAlcoholic[i] = drink.getString("strAlcoholic");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StringBuilder resultString = new StringBuilder("");
            for (int i = 0; i < strDrink.length; i++) {
                String tmp = "Cocktail: " + strDrink[i] + "\n" +
                        "Category: " + strCategory[i] + "\n" + "Alcoholic: " + strAlcoholic[i];
                resultString.append(tmp).append("\n").append("=========").append("\n");
            }

            result.setText(resultString.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        searchField = findViewById(R.id.drink_search_field);
        searchButton = findViewById(R.id.btnSearchDrink);
        result = findViewById(R.id.tvSearchResult);
        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        URL generatedURL = NetUtils.generateURL(searchField.getText().toString());
        new DrinkQueryTask().execute(generatedURL);

    }
}
