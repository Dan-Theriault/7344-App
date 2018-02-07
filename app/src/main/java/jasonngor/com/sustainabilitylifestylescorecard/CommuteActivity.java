package jasonngor.com.sustainabilitylifestylescorecard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CommuteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commute);
    }

    public void onSubmitCommutePress(View v) {
        Toast.makeText(this,
                "Congratulations! You have earned 6 earth bucks",
                Toast.LENGTH_LONG).show();
    }
}
