package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DashboardActivity extends AppCompatActivity {

    public int scorecard;

    public void computeScore(int a) {
        scorecard += a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        scorecard = 0;
        Intent prevIntent = getIntent();
        int input = prevIntent.getExtras().getInt("activity");
        computeScore(input);

        TextView score = (TextView) findViewById(R.id.textView5);
        score.setText("" + scorecard);
    }

    public void onCommutePress(View v) {
        Intent commuteIntent = new Intent(this, CommuteActivity.class);
        startActivity(commuteIntent);
    }

    public void onWaterBillPress(View v) {
        Intent waterBillIntent = new Intent(this, WaterBillActivity.class);
        startActivity(waterBillIntent);
    }

    public void onElectricityPress(View v) {
        Intent electricityIntent = new Intent(this, ElectricityActivity.class);
        startActivity(electricityIntent);
    }

    public void onJournalPress(View v) {
        Intent journalIntent = new Intent(this, JournalActivity.class);
        startActivity(journalIntent);
    }

    public void onDietPress(View v) {
        Intent dietIntent = new Intent(this, DietActivity.class);
        startActivity(dietIntent);
    }

    public void onHealthPress(View v) {
        Intent healthIntent = new Intent(this, HealthActivity.class);
        startActivity(healthIntent);
    }
}
