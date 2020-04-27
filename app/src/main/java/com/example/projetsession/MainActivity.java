package com.example.projetsession;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetsession.adapter.CustomRecipeAdapter;
import com.example.projetsession.model.Recipe;


import org.json.JSONObject;

import java.util.ArrayList;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        AutoLogin();
    }


    public void idConfirm(View v){
        CallLogin();
    }

    private void CallLogin() {
        RequestQueue queue = Volley.newRequestQueue(this);

        TextView name = findViewById(R.id.idName);
        TextView password = findViewById(R.id.password);

        String url = "http://hansiv4.ddns.net:3000/user/"+name.getText()+"/"+password.getText();
        Log.i("DIM",url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("DIM", "Response: " + response.toString());
                            if (response.getBoolean("loggedIn")){
                                Intent intent = new Intent(MainActivity.this, feedActivity.class);

                                String message = response.getString("userId");
                                intent.putExtra("userId", message);
                                MainActivity.this.startActivity(intent);
                            }
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("ERROR", error.toString());
                    }
                });

        queue.add(jsonObjectRequest);
    }

    private void AutoLogin (){
        EditText myEditText = (EditText)findViewById(R.id.password);
        myEditText.setOnEditorActionListener(TextView v, int actionId, KeyEvent event); {

        });
        /*((EditText)findViewById(R.id.password)).setOnFocusChangeListener(new View.OnFocusChangeListener){
            //@Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){
                    CallLogin();
                }
            }
        }*/
    }
}