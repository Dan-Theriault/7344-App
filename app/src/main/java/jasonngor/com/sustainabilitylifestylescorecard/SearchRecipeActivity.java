package jasonngor.com.sustainabilitylifestylescorecard;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;


import okhttp3.*;

public class SearchRecipeActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);
    }

    private void searchRecipe(String query) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.edamam.com/search?q=" + query + "&app_id=6923b1fc&app_key=24de1ec68de7503a8b9e44374d9ccfe5")
                .get()
                .addHeader("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                String mMessage = response.body().string();
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(mMessage);
                        Log.d("json response", json.toString(4));
                    } catch (Exception e) {
                        String eMessage = e.getMessage().toString();
                        Log.w("failure Response", eMessage);
                    }
                }
            }
        });

    }


}
