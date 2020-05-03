package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import java.text.ParseException;

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
            Drink[] drinksFound = null;
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray drinks = jsonResponse.getJSONArray("drinks");
                JSONObject drink;
                drinksFound = new Drink[drinks.length()];
                for (int i = 0; i < drinks.length(); i++) {
                    drinksFound[i] = getDrink(drinks.getJSONObject(i));
                }
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
            StringBuilder resultString = new StringBuilder("");
            if (drinksFound != null && drinksFound.length > 0) {
                for (int i = 0; i < drinksFound.length; i++) {
                    String tmp = "Cocktail: " + drinksFound[i].getStrDrink() + "\n" +
                            "Category: " + drinksFound[i].getStrCategory() + "\n" + "Alcoholic: " + drinksFound[i].getStrAlcoholic();
                    resultString.append(tmp).append("\n").append("=========").append("\n");
                }
            } else {
                resultString.append("No cocktails found");
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

    private static Drink getDrink(JSONObject jsonObject) throws JSONException, ParseException {
        Drink drink = new Drink();
        drink.setIdDrink(jsonObject.getString("idDrink"));
        drink.setStrDrink(jsonObject.getString("strDrink"));
        drink.setStrDrinkAlternate(jsonObject.getString("strDrinkAlternate"));
        drink.setStrTags(jsonObject.getString("strTags"));
        drink.setStrCategory(jsonObject.getString("strCategory"));
        drink.setStrIBA(jsonObject.getString("strIBA"));
        drink.setStrAlcoholic(jsonObject.getString("strAlcoholic"));
        drink.setStrGlass(jsonObject.getString("strGlass"));
        drink.setStrInstructions(jsonObject.getString("strInstructions"));
        drink.setStrDrinkThumb(jsonObject.getString("strDrinkThumb"));
        drink.setIngredient1(jsonObject.getString("strIngredient1"));
        drink.setIngredient2(jsonObject.getString("strIngredient2"));
        drink.setIngredient3(jsonObject.getString("strIngredient3"));
        drink.setIngredient4(jsonObject.getString("strIngredient4"));
        drink.setIngredient5(jsonObject.getString("strIngredient5"));
        drink.setIngredient6(jsonObject.getString("strIngredient6"));
        drink.setIngredient7(jsonObject.getString("strIngredient7"));
        drink.setIngredient8(jsonObject.getString("strIngredient8"));
        drink.setIngredient9(jsonObject.getString("strIngredient9"));
        drink.setIngredient10(jsonObject.getString("strIngredient10"));
        drink.setIngredient11(jsonObject.getString("strIngredient11"));
        drink.setIngredient12(jsonObject.getString("strIngredient12"));
        drink.setIngredient13(jsonObject.getString("strIngredient13"));
        drink.setIngredient14(jsonObject.getString("strIngredient14"));
        drink.setIngredient15(jsonObject.getString("strIngredient15"));
        drink.setMeasure1(jsonObject.getString("strMeasure1"));
        drink.setMeasure2(jsonObject.getString("strMeasure2"));
        drink.setMeasure3(jsonObject.getString("strMeasure3"));
        drink.setMeasure4(jsonObject.getString("strMeasure4"));
        drink.setMeasure5(jsonObject.getString("strMeasure5"));
        drink.setMeasure6(jsonObject.getString("strMeasure6"));
        drink.setMeasure7(jsonObject.getString("strMeasure7"));
        drink.setMeasure8(jsonObject.getString("strMeasure8"));
        drink.setMeasure9(jsonObject.getString("strMeasure9"));
        drink.setMeasure10(jsonObject.getString("strMeasure10"));
        drink.setMeasure11(jsonObject.getString("strMeasure11"));
        drink.setMeasure12(jsonObject.getString("strMeasure12"));
        drink.setMeasure13(jsonObject.getString("strMeasure13"));
        drink.setMeasure14(jsonObject.getString("strMeasure14"));
        drink.setMeasure15(jsonObject.getString("strMeasure15"));
        drink.setStrCreativeCommonsConfirmed(jsonObject.getString("strCreativeCommonsConfirmed"));
        drink.setDateModified(jsonObject.getString("dateModified"));
        return drink;
    }
}
