package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barcoctail.utils.NetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class SearchScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText searchField;
    private Button searchButton;
    private TextView result;
    private ListView listView;
    private ArrayList<Drink> drinksFound = null;
    private DBHelper dbHelper;

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
            try {
                JSONObject jsonResponse = new JSONObject(response);
                JSONArray drinks = jsonResponse.getJSONArray("drinks");
                JSONObject drink;
                drinksFound = new ArrayList<>();
                for (int i = 0; i < drinks.length(); i++) {
                    drinksFound.add(getDrink(drinks.getJSONObject(i)));
                }
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(SearchScreen.this,
                        "No internet connection or resource not found", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            if (drinksFound == null || drinksFound.size() == 0) {
                result.setText(R.string.noCocktFound);
            } else {
                result.setText("");
                final ListView mListView = (ListView) findViewById(R.id.lvSearchedDrinks);
                DrinkListAdapter drinkAdapter = new DrinkListAdapter(SearchScreen.this, R.layout.drinks_found, drinksFound);
                mListView.setAdapter(drinkAdapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        DrinkInfoActivity infoActivity = new DrinkInfoActivity();
                        Drink drink = (Drink) mListView.getItemAtPosition(position);
                        SQLiteDatabase database = dbHelper.getWritableDatabase();

                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DBHelper.KEY_ID, drink.getIdDrink());
                        contentValues.put(DBHelper.KEY_NAME, drink.getStrDrink());
                        contentValues.put(DBHelper.KEY_CATEGORY, drink.getStrCategory());
                        contentValues.put(DBHelper.KEY_ALCOHOL, drink.getStrAlcoholic());
                        contentValues.put(DBHelper.KEY_GLASS, drink.getStrGlass());
                        contentValues.put(DBHelper.KEY_INSTRUCTIONS, drink.getStrInstructions());
                        contentValues.put(DBHelper.KEY_IMG, drink.getStrDrinkThumb());
                        contentValues.put(DBHelper.KEY_ING1, drink.getIngredient1());
                        contentValues.put(DBHelper.KEY_ING2, drink.getIngredient2());
                        contentValues.put(DBHelper.KEY_ING3, drink.getIngredient3());
                        contentValues.put(DBHelper.KEY_ING4, drink.getIngredient4());
                        contentValues.put(DBHelper.KEY_ING5, drink.getIngredient5());
                        contentValues.put(DBHelper.KEY_ING6, drink.getIngredient6());
                        contentValues.put(DBHelper.KEY_ING7, drink.getIngredient7());
                        contentValues.put(DBHelper.KEY_ING8, drink.getIngredient8());
                        contentValues.put(DBHelper.KEY_ING9, drink.getIngredient9());
                        contentValues.put(DBHelper.KEY_ING10, drink.getIngredient10());
                        contentValues.put(DBHelper.KEY_ING11, drink.getIngredient11());
                        contentValues.put(DBHelper.KEY_ING12, drink.getIngredient12());
                        contentValues.put(DBHelper.KEY_ING13, drink.getIngredient13());
                        contentValues.put(DBHelper.KEY_ING14, drink.getIngredient14());
                        contentValues.put(DBHelper.KEY_ING15, drink.getIngredient15());
                        contentValues.put(DBHelper.KEY_MEASURE1, drink.getMeasure1());
                        contentValues.put(DBHelper.KEY_MEASURE2, drink.getMeasure2());
                        contentValues.put(DBHelper.KEY_MEASURE3, drink.getMeasure3());
                        contentValues.put(DBHelper.KEY_MEASURE4, drink.getMeasure4());
                        contentValues.put(DBHelper.KEY_MEASURE5, drink.getMeasure5());
                        contentValues.put(DBHelper.KEY_MEASURE6, drink.getMeasure6());
                        contentValues.put(DBHelper.KEY_MEASURE7, drink.getMeasure7());
                        contentValues.put(DBHelper.KEY_MEASURE8, drink.getMeasure8());
                        contentValues.put(DBHelper.KEY_MEASURE9, drink.getMeasure9());
                        contentValues.put(DBHelper.KEY_MEASURE10, drink.getMeasure10());
                        contentValues.put(DBHelper.KEY_MEASURE11, drink.getMeasure11());
                        contentValues.put(DBHelper.KEY_MEASURE12, drink.getMeasure12());
                        contentValues.put(DBHelper.KEY_MEASURE13, drink.getMeasure13());
                        contentValues.put(DBHelper.KEY_MEASURE14, drink.getMeasure14());
                        contentValues.put(DBHelper.KEY_MEASURE15, drink.getMeasure15());
                        database.insert(DBHelper.TABLE_DRINKS, null, contentValues);
                        dbHelper.close();

                        ArrayList<Ingredients> ingredients = NetUtils.getIngredientsList(drink);

                        Intent intent = new Intent(SearchScreen.this, DrinkInfoActivity.class);
                        intent.putExtra("name", drink.getStrDrink());
                        intent.putExtra("alcohol", drink.getStrAlcoholic());
                        intent.putExtra("glass", drink.getStrGlass());
                        intent.putExtra("instruction", drink.getStrInstructions());
                        intent.putExtra("imgURL", drink.getStrDrinkThumb());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ingredients", ingredients);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);

        searchField = findViewById(R.id.drink_search_field);
        searchButton = findViewById(R.id.btnSearchDrink);
        result = findViewById(R.id.resultSearch);
        searchButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        drink.setStrCategory(jsonObject.getString("strCategory"));
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
        return drink;
    }
}
