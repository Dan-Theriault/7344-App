package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DietEntryActivity extends AppCompatActivity {

    private EditText foodTxt;
    private EditText kcalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_entry);
        foodTxt = (EditText) findViewById(R.id.foodTxt);
        kcalTxt = (EditText) findViewById(R.id.kcalTxt);
    }

    public void searchRecipe(View v) {

    }

    public void addMeal(View v) {
        String foodName = foodTxt.getText().toString();
        String kcal = kcalTxt.getText().toString();
        Context context = DietEntryActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences("DietEntries", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("entry", foodName + "," + kcal);
        editor.commit();
        finish();
    }

    public void cancel(View v) {
        finish();
    }
}
