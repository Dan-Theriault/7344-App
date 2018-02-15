package jasonngor.com.sustainabilitylifestylescorecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CommuteEntryActivity extends AppCompatActivity {

    public static EditText distance;
    public static Spinner methodSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commute_entry);
        distance = (EditText) findViewById(R.id.et_commute_distance);
        //get the spinner from the xml.
        methodSpinner = (Spinner) findViewById(R.id.commute_method_spinner);
        //create a list of items for the spinner.
        String[] items = new String[]{"Walking/Running", "Car", "Carpool", "Bicycle"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        methodSpinner.setAdapter(adapter);
    }

    public void onSubmitCommutePress(View v) {
        String entries = distance.getText().toString();
        String method = methodSpinner.getSelectedItem().toString();
        entries = method + "        " + entries + " miles";

        CommuteActivity.list.add(entries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CommuteEntryActivity.this, android.R.layout.simple_list_item_1,
                CommuteActivity.list);
        CommuteActivity.show.setAdapter(adapter);

        Toast.makeText(this,
                "Congratulations! You have earned 6 earth bucks",
                Toast.LENGTH_LONG).show();
    }

}