package com.example.barcoctail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.barcoctail.utils.NetUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSearch;
    private ArrayList<Drink> drinksFromDB;
    private DBHelper dbHelper;
    private ListView listView;
    private TextView textViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMain = (TextView) findViewById(R.id.textViewMain);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        dbHelper = new DBHelper(this);
        drinksFromDB = getDrinksFromBD(dbHelper);
        if (drinksFromDB != null || drinksFromDB.size() > 0) {
            textViewMain.setText("");
        }
        listView = (ListView) findViewById(R.id.lvDrinksFromDB);
        DrinkListAdapter drinkAdapter = new DrinkListAdapter(MainActivity.this, R.layout.drinks_found, drinksFromDB);
        listView.setAdapter(drinkAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drink drink = (Drink) listView.getItemAtPosition(position);
                ArrayList<Ingredients> ingredients = NetUtils.getIngredientsList(drink);
                Intent intent = new Intent(MainActivity.this, DrinkInfoActivity.class);
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
        dbHelper.close();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearch:
                Intent intent = new Intent(this, SearchScreen.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private static ArrayList<Drink> getDrinksFromBD(DBHelper dbHelper) {
        ArrayList<Drink> drinks = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_DRINKS, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int categoryIndex = cursor.getColumnIndex(DBHelper.KEY_CATEGORY);
            int alcoholIndex = cursor.getColumnIndex(DBHelper.KEY_ALCOHOL);
            int glassIndex = cursor.getColumnIndex(DBHelper.KEY_GLASS);
            int instructionIndex = cursor.getColumnIndex(DBHelper.KEY_INSTRUCTIONS);
            int imgIndex = cursor.getColumnIndex(DBHelper.KEY_IMG);
            int ingredientIndex1 = cursor.getColumnIndex(DBHelper.KEY_ING1);
            int ingredientIndex2 = cursor.getColumnIndex(DBHelper.KEY_ING2);
            int ingredientIndex3 = cursor.getColumnIndex(DBHelper.KEY_ING3);
            int ingredientIndex4 = cursor.getColumnIndex(DBHelper.KEY_ING4);
            int ingredientIndex5 = cursor.getColumnIndex(DBHelper.KEY_ING5);
            int ingredientIndex6 = cursor.getColumnIndex(DBHelper.KEY_ING6);
            int ingredientIndex7 = cursor.getColumnIndex(DBHelper.KEY_ING7);
            int ingredientIndex8 = cursor.getColumnIndex(DBHelper.KEY_ING8);
            int ingredientIndex9 = cursor.getColumnIndex(DBHelper.KEY_ING9);
            int ingredientIndex10 = cursor.getColumnIndex(DBHelper.KEY_ING10);
            int ingredientIndex11 = cursor.getColumnIndex(DBHelper.KEY_ING11);
            int ingredientIndex12 = cursor.getColumnIndex(DBHelper.KEY_ING12);
            int ingredientIndex13 = cursor.getColumnIndex(DBHelper.KEY_ING13);
            int ingredientIndex14 = cursor.getColumnIndex(DBHelper.KEY_ING14);
            int ingredientIndex15 = cursor.getColumnIndex(DBHelper.KEY_ING15);
            int measureIndex1 = cursor.getColumnIndex(DBHelper.KEY_MEASURE1);
            int measureIndex2 = cursor.getColumnIndex(DBHelper.KEY_MEASURE2);
            int measureIndex3 = cursor.getColumnIndex(DBHelper.KEY_MEASURE3);
            int measureIndex4 = cursor.getColumnIndex(DBHelper.KEY_MEASURE4);
            int measureIndex5 = cursor.getColumnIndex(DBHelper.KEY_MEASURE5);
            int measureIndex6 = cursor.getColumnIndex(DBHelper.KEY_MEASURE6);
            int measureIndex7 = cursor.getColumnIndex(DBHelper.KEY_MEASURE7);
            int measureIndex8 = cursor.getColumnIndex(DBHelper.KEY_MEASURE8);
            int measureIndex9 = cursor.getColumnIndex(DBHelper.KEY_MEASURE9);
            int measureIndex10 = cursor.getColumnIndex(DBHelper.KEY_MEASURE10);
            int measureIndex11 = cursor.getColumnIndex(DBHelper.KEY_MEASURE11);
            int measureIndex12 = cursor.getColumnIndex(DBHelper.KEY_MEASURE12);
            int measureIndex13 = cursor.getColumnIndex(DBHelper.KEY_MEASURE13);
            int measureIndex14 = cursor.getColumnIndex(DBHelper.KEY_MEASURE14);
            int measureIndex15 = cursor.getColumnIndex(DBHelper.KEY_MEASURE15);
            do {
                drinks.add(new Drink(cursor.getString(idIndex), cursor.getString(nameIndex),
                        cursor.getString(categoryIndex), cursor.getString(alcoholIndex),
                        cursor.getString(glassIndex),
                        cursor.getString(instructionIndex), cursor.getString(imgIndex),
                        cursor.getString(ingredientIndex1), cursor.getString(ingredientIndex2),
                        cursor.getString(ingredientIndex3), cursor.getString(ingredientIndex4),
                        cursor.getString(ingredientIndex5), cursor.getString(ingredientIndex6),
                        cursor.getString(ingredientIndex7), cursor.getString(ingredientIndex8),
                        cursor.getString(ingredientIndex9), cursor.getString(ingredientIndex10),
                        cursor.getString(ingredientIndex11), cursor.getString(ingredientIndex12),
                        cursor.getString(ingredientIndex13), cursor.getString(ingredientIndex14),
                        cursor.getString(ingredientIndex15),
                        cursor.getString(measureIndex1),
                        cursor.getString(measureIndex2), cursor.getString(measureIndex3),
                        cursor.getString(measureIndex4), cursor.getString(measureIndex5),
                        cursor.getString(measureIndex6), cursor.getString(measureIndex7),
                        cursor.getString(measureIndex8), cursor.getString(measureIndex9),
                        cursor.getString(measureIndex10), cursor.getString(measureIndex11),
                        cursor.getString(measureIndex12), cursor.getString(measureIndex13),
                        cursor.getString(measureIndex14), cursor.getString(measureIndex15)));
            } while (cursor.moveToNext());
            return drinks;
        }
        cursor.close();
        return null;
    }
}
