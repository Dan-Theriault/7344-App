package jasonngor.com.sustainabilitylifestylescorecard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.*;

public class SearchRecipeActivity extends AppCompatActivity {
    private EditText recipeName;
    private RecyclerView recyclerView;
    private ArrayList<String> recipeArr;
    private ArrayList<Integer> kcalArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_recipe);
        recipeName = (EditText) findViewById(R.id.searchRecipeTxt);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void searchRecipe(String query) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.edamam.com/search?q=" + query + "&app_id=6923b1fc&app_key=24de1ec68de7503a8b9e44374d9ccfe5&to=30")
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
                        final JSONArray recipes = json.getJSONArray("hits");
                        recipeArr = new ArrayList<>();
                        kcalArr = new ArrayList<>();
                        for (int i = 0; i < recipes.length(); i++) {
                            JSONObject hit = recipes.getJSONObject(i);
                            JSONObject recipe = hit.getJSONObject("recipe");
                            if (!recipeArr.contains(recipe.getString("label"))) {
                                recipeArr.add(recipe.getString("label"));
                                kcalArr.add(recipe.getInt("calories"));
                            }
                        }
                        Log.d("numRecipes", "" + recipeArr.size());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                RecipesAdapter adapter = new RecipesAdapter(SearchRecipeActivity.this, recipeArr, kcalArr);
                                recyclerView.setAdapter(adapter);
                                LinearLayoutManager layoutManager = new LinearLayoutManager(SearchRecipeActivity.this);
                                recyclerView.setLayoutManager(layoutManager);
                                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                                recyclerView.addItemDecoration(dividerItemDecoration);
                            }
                        });

                    } catch (Exception e) {
                        String eMessage = e.getMessage().toString();
                        Log.w("failure Response", eMessage);
                    }
                }
            }
        });

    }


    public void onSearchRecipeClick(View view) {
        searchRecipe(recipeName.getText().toString());
    }


    // RecyclerView stuff
    public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {
        private ArrayList<String> mRecipeArr;
        private ArrayList<Integer> mKcalArr;
        private Context mContext;


        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView recipeNameTxt;
            public TextView kcalTxt;
            public LinearLayout parentLayout;

            public ViewHolder(View itemView) {
                super(itemView);
                recipeNameTxt = (TextView) itemView.findViewById(R.id.recipeNameTxt);
                kcalTxt = (TextView) itemView.findViewById(R.id.kcalTxt);
                parentLayout = (LinearLayout) itemView.findViewById(R.id.parentLayout);
            }
        }

        private RecipesAdapter(Context context, ArrayList<String> recipeArr, ArrayList<Integer> kcalArr) {
            mContext = context;
            mRecipeArr = recipeArr;
            mKcalArr = kcalArr;
        }

        private Context getContext() {
            return mContext;
        }

        // Inflate the xml and return the holder
        @Override
        public RecipesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View recipeView = inflater.inflate(R.layout.item_recipe, parent, false);

            // Return a new holder instance
            RecipesAdapter.ViewHolder viewHolder = new RecipesAdapter.ViewHolder(recipeView);
            return viewHolder;
        }

        // Populate data into the item through holder
        @Override
        public void onBindViewHolder(RecipesAdapter.ViewHolder holder, final int position) {
            // Get data model based on position
            final String recipeName = mRecipeArr.get(position);
            final int recipeKcal = mKcalArr.get(position);

            // Set item views
            holder.recipeNameTxt.setText(recipeName);
            holder.kcalTxt.setText(recipeKcal + "kcal");

            holder.parentLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d("position", "" + position);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("recipeName", recipeName);
                    returnIntent.putExtra("calories", recipeKcal);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mRecipeArr.size();
        }

    }
}
