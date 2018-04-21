package jasonngor.com.sustainabilitylifestylescorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText PassView;
    private EditText EmailView;

    private String Email;
    private String Password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailView = (EditText) findViewById(R.id.et_email_login);
        PassView = (EditText) findViewById(R.id.et_password_login);


        EmailView.setText("User123");
        PassView.setText("Pass456");

    }

    public void onSubmitLoginPress(View v) {
        Email = EmailView.getText().toString();
        Password = PassView.getText().toString();

        Toast.makeText(this,
                "Loging in",
                Toast.LENGTH_LONG).show();


        Intent dashboardIntent = new Intent(this, DashboardActivity.class);
        dashboardIntent.putExtra("activity", 0);
        startActivity(dashboardIntent);

    }

    public void onBackPress(View v) {
        Intent landingIntent = new Intent(this, LandingActivity.class);
        startActivity(landingIntent);
    }
}
