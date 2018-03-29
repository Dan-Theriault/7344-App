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

public class ElectricityEntryActivity extends AppCompatActivity {

    public static EditText data;
    public static EditText date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity_entry);

        data = (EditText) findViewById(R.id.editText);
        date = (EditText) findViewById(R.id.enterDate);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entries = data.getText().toString() + " Hours        Date: ";
                String dates = date.getText().toString();
                entries = entries + dates;

                if (entries == null || entries.equals(" Hours        Date: ")) {
                    Toast.makeText(getBaseContext(), "Cannot submit empty field", Toast.LENGTH_LONG).show();
                } else if (dates == null|| dates.equals("")) {
                    Toast.makeText(getBaseContext(), "Please enter a date", Toast.LENGTH_LONG).show();
                } else {
                    ElectricityActivity.list.add(entries);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ElectricityEntryActivity.this, android.R.layout.simple_list_item_1, ElectricityActivity.list);
                    ElectricityActivity.show.setAdapter(adapter);
                    ((EditText)findViewById(R.id.editText)).setText("TV/Computer/Entertainment Usage (Hours)");
                    ((EditText)findViewById(R.id.enterDate)).setText("Date (mm/dd/yy)");
                    Toast.makeText(getBaseContext(), "New entry added!", Toast.LENGTH_LONG).show();

                    Intent electricIntent = new Intent(ElectricityEntryActivity.this, ElectricityActivity.class);
                    startActivity(electricIntent);
                }


            }
        });

    }

}
