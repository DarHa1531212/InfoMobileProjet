package com.example.projetsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class preferences extends AppCompatActivity {

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");

    }


    public void updatePreferences(View v) {


        String url = "http://hansiv4.ddns.net:3000/updateUser/" + userId;
        Log.i("DIM", url);

        RequestQueue queue = Volley.newRequestQueue(this);

        CheckBox cake = findViewById(R.id.cake);
        CheckBox soup = findViewById(R.id.soup);
        CheckBox crepe = findViewById(R.id.crepe);

        boolean cakeIsOn = cake.isChecked();
        boolean soupIsOn = soup.isChecked();
        boolean crepeIsOn = crepe.isChecked();


        JSONObject postParam = new JSONObject();
        try {
            postParam.put("userId", userId);
            postParam.put("cake", cakeIsOn);
            postParam.put("soup", soupIsOn);
            postParam.put("crepe", crepeIsOn);
        } catch (JSONException err) {
            err.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.PUT,
                url,
                postParam,
                new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("DIM", response.toString());
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjReq);
    }
}
