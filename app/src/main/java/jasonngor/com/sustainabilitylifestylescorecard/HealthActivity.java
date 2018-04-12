package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by dianamilton on 3/29/18.
 */

public class HealthActivity extends AppCompatActivity {

    public static EditText cigsPerDayQ;
    public static EditText numCigs;
    public static CheckBox cigs;
    public static Button save;
    public static Spinner clothesSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        cigsPerDayQ = (EditText) findViewById(R.id.editText3);
        numCigs = (EditText) findViewById(R.id.editText4);
        cigs = (CheckBox) findViewById(R.id.smokingBox);
        //get the spinner from the xml.
        clothesSpinner = (Spinner) findViewById(R.id.spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"Less than 33", "33 - 50", "51 - 74", "Greater than 75"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        clothesSpinner.setAdapter(adapter);
        save = (Button) findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entryIntent;
                entryIntent = new Intent(HealthActivity.this, DashboardActivity.class);
                startActivity(entryIntent);
            }
        });
    }

}
