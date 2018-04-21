package jasonngor.com.sustainabilitylifestylescorecard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class DietEntryActivity extends AppCompatActivity {

    private EditText foodTxt;
    public static EditText kcalTxt;
    private CheckBox plantBasedCheckbox;

    private static final int SEARCH_RECIPE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_entry);
        foodTxt = (EditText) findViewById(R.id.foodTxt);
        kcalTxt = (EditText) findViewById(R.id.kcalTxt);
        plantBasedCheckbox = (CheckBox) findViewById(R.id.plantBasedCheckbox);
    }

    public void onSearchRecipeClick(View v) {
        Intent intent = new Intent(DietEntryActivity.this, SearchRecipeActivity.class);
        startActivityForResult(intent, SEARCH_RECIPE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SEARCH_RECIPE_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                foodTxt.setText(data.getStringExtra("recipeName"));
                kcalTxt.setText(Integer.toString(data.getIntExtra("calories", 0)));
            }
        }
    }

    public void onAddMealClick(View v) {
        String foodName = foodTxt.getText().toString();
        String kcal = kcalTxt.getText().toString();
        Context context = DietEntryActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences("DietEntries", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("entry", foodName + "," + kcal + "," + plantBasedCheckbox.isChecked());
        editor.commit();
        finish();
    }

    public void onCancelClick(View v) {
        finish();
    }
}
