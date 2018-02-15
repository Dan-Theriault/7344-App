package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DietActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private TextView dietEntryTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        fab = (FloatingActionButton) findViewById(R.id.addDietFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dietEntryIntent = new Intent(DietActivity.this, DietEntryActivity.class);
                startActivity(dietEntryIntent);
            }
        });

        dietEntryTv = (TextView) findViewById(R.id.dietEntryTv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = DietActivity.this.getSharedPreferences("DietEntries", Context.MODE_PRIVATE);
        String entryString = sharedPref.getString("entry", "");
        dietEntryTv.setText(entryString);
    }
}
