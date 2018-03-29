package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by dianamilton on 3/29/18.
 */

public class HealthActivity extends AppCompatActivity {

    public static EditText cigsPerDayQ;
    public static EditText numCigs;
    public static CheckBox cigs;
    public static Button save;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        cigsPerDayQ = (EditText) findViewById(R.id.editText3);
        numCigs = (EditText) findViewById(R.id.editText4);
        cigs = (CheckBox) findViewById(R.id.smokingBox);
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
