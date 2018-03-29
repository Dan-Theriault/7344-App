package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class WaterBillEntryActivity extends AppCompatActivity {

    public static EditText data;
    public static EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bill_entry);
        data = (EditText) findViewById(R.id.editText2);
        date = (EditText) findViewById(R.id.enterwdate);


        FloatingActionButton waterButton = (FloatingActionButton) findViewById(R.id.waterButton);
        waterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entries = data.getText().toString() + " Cups        Date: ";
                String dates = date.getText().toString();
                entries = entries + dates;

                if (entries == null || entries.equals(" Cups        Date: ")) {
                    Toast.makeText(getBaseContext(), "Cannot submit empty field", Toast.LENGTH_LONG).show();
                } else if (dates == null||dates.equals("")) {
                    Toast.makeText(getBaseContext(), "Please enter a date", Toast.LENGTH_LONG).show();
                } else {
                    WaterBillActivity.list.add(entries);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(WaterBillEntryActivity.this, android.R.layout.simple_list_item_1, WaterBillActivity.list);
                    WaterBillActivity.show.setAdapter(adapter);
                    ((EditText)findViewById(R.id.editText2)).setText("Water Consumed Today (Cups)");
                    ((EditText)findViewById(R.id.enterwdate)).setText("Date (mm/dd/yy)");
                    Toast.makeText(getBaseContext(), "New entry added!", Toast.LENGTH_LONG).show();

                    Intent electricIntent = new Intent(WaterBillEntryActivity.this, ElectricityActivity.class);
                    startActivity(electricIntent);
                }

            }
        });
    }

}
