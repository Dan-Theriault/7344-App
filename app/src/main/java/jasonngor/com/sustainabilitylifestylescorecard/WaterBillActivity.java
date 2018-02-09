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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WaterBillActivity extends AppCompatActivity {

    public static ListView waterEntries;
    public static ArrayList<String> list = new ArrayList<>();
    EditText data = WaterBillEntryActivity.data;
    public static ListView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_bill);
        show = (ListView)findViewById(R.id.water_bill_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(WaterBillActivity.this, android.R.layout.simple_list_item_1, list);
        show.setAdapter(adapter);

        FloatingActionButton wEntry = (FloatingActionButton) findViewById(R.id.wEntry);
        wEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entryIntent;
                entryIntent = new Intent(WaterBillActivity.this, WaterBillEntryActivity.class);
                startActivity(entryIntent);
            }
        });
    }

}
