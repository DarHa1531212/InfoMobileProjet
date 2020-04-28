package com.example.projetsession;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetsession.adapter.CustomRecipeAdapter;
import com.example.projetsession.model.Recipe;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class feedActivity extends AppCompatActivity {

    CustomRecipeAdapter adapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe_list);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        Log.v("DIM", "user: " + userId);

        adapter = setListAdapter();
        getRecipes(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_feed_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.settings) {
            Intent intent = new Intent(feedActivity.this, preferences.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
            return true;
        } else if(item.getItemId() == R.id.refresh) {
            refresh();
            return true;
        }
        return false;
    }


    private CustomRecipeAdapter setListAdapter() {
        ArrayList<Recipe> arrayOfRecipe = new ArrayList<Recipe>();

        final CustomRecipeAdapter adapter = new CustomRecipeAdapter(this, arrayOfRecipe);

        ListView listView = (ListView) findViewById(R.id.recipeList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO
                Intent intent = new Intent(feedActivity.this, recipeActivity.class);
                intent.putExtra("recipeId", adapter.getItem(position).getId());
                startActivity(intent);
            }
        });

        return adapter;
    }

    private void getRecipes(final CustomRecipeAdapter adapter) {
        final ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://hansiv4.ddns.net:3000/recettes/" + userId;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Recipe tempRecipe = new Recipe();
                        JSONObject recipe = response.getJSONObject(i);

                        String title = recipe.getString("nomRecette");
                        String description = recipe.getString("txtRecette");
                        String id = recipe.getString("idRecette");
                        try {
                            String encodedImage = recipe.getString("image");
                            byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
                            Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                            tempRecipe.setImage(decodedImage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tempRecipe.setTitle(title);
                        tempRecipe.setDescription(description);
                        tempRecipe.setId((id));

                        recipes.add(tempRecipe);
                    }
                    adapter.clear();
                    adapter.addAll(recipes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("DIM", String.valueOf(error));
            }
        });

        queue.add(jsonArrayRequest);
    }


    public void refresh() {
        getRecipes(adapter);
    }
}
