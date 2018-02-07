package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
}
