package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class CommuteActivity extends AppCompatActivity {

    public static ListView commuteEntries;
    public static ArrayList<String> list = new ArrayList<>();
    public static ListView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commute);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        show = (ListView)findViewById(R.id.commute_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CommuteActivity.this, android.R.layout.simple_list_item_1, list);
        show.setAdapter(adapter);

        FloatingActionButton cEntry = (FloatingActionButton) findViewById(R.id.cEntry);
        cEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entryIntent;
                entryIntent = new Intent(CommuteActivity.this, CommuteEntryActivity.class);
                startActivity(entryIntent);
            }
        });
    }

}
