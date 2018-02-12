package jasonngor.com.sustainabilitylifestylescorecard;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ElectricityActivity extends AppCompatActivity {

    public static ListView electricityEntries;
    public static ArrayList<String> list = new ArrayList<>();
    EditText data = ElectricityEntryActivity.data;
    public static ListView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricity);
        show = (ListView)findViewById(R.id.electricity_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ElectricityActivity.this, android.R.layout.simple_list_item_1, list);
        show.setAdapter(adapter);

        FloatingActionButton eEntry = (FloatingActionButton) findViewById(R.id.eEntry);
        eEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entryIntent;
                entryIntent = new Intent(ElectricityActivity.this, ElectricityEntryActivity.class);
                startActivity(entryIntent);
            }
        });
    }



}
