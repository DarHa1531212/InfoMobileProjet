package com.example.projetsession;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class recipeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        Intent intent = getIntent();
        String recipeId = intent.getStringExtra("recipeId");

        setRecipe(recipeId);
    }

    private void setRecipe(String id) {

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://hansiv4.ddns.net:3000/singleRecette/" + id;

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            TextView recipeTitle = findViewById(R.id.singleRecipeTitle);
                            TextView recipeDescription = findViewById(R.id.singleRecipeDescription);
                            ImageView recipeImage = findViewById(R.id.singleRecipeImage);

                            JSONObject recipe = response.getJSONObject(0);
                            Log.i("DIM", "Response: " + recipe.getString("txtRecette"));


                            recipeTitle.setText(recipe.getString("nomRecette"));
                            //TODO description doesnt show
                            recipeDescription.setText(recipe.getString("txtRecette"));
                            try {
                                String encodedImage = recipe.getString("image");
                                byte[] decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT);
                                Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                                recipeImage.setImageBitmap(decodedImage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("ERROR", error.toString());
                    }
                });

                queue.add(jsonObjectRequest);
    }
}
