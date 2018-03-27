package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DietEntryActivity extends AppCompatActivity {

    private EditText foodTxt;
    private EditText kcalTxt;

    private static final int SEARCH_RECIPE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_entry);
        foodTxt = (EditText) findViewById(R.id.foodTxt);
        kcalTxt = (EditText) findViewById(R.id.kcalTxt);
    }

    public void onSearchRecipeClick(View v) {
        Intent intent = new Intent(DietEntryActivity.this, SearchRecipeActivity.class);
        startActivityForResult(intent, SEARCH_RECIPE_REQUEST);
    }

    public void onAddMealClick(View v) {
        String foodName = foodTxt.getText().toString();
        String kcal = kcalTxt.getText().toString();
        Context context = DietEntryActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences("DietEntries", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("entry", foodName + "," + kcal);
        editor.commit();
        finish();
    }

    public void onCancelClick(View v) {
        finish();
    }
}
