package com.example.projetsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class preferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    private void updateProfile(){
        Log.i("testing", "success");
    }

    private void updatePreferences() {
        RequestQueue queue = Volley.newRequestQueue(this);

        CheckBox cake = findViewById(R.id.cake);
        CheckBox soup = findViewById(R.id.soup);
        CheckBox crepe = findViewById(R.id.crepe);

        boolean cakeIsOn = cake.isChecked();
        boolean soupIsOn = soup.isChecked();
        boolean crepeIsOn = crepe.isChecked();
        RequestBody myRequest = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("cake", String.valueOf(cakeIsOn))
                .addFormDataPart("soup", String.valueOf(soupIsOn))
                .addFormDataPart("crepe", String.valueOf(crepeIsOn))
                .build();

        Request request = new Request.Builder()
                .url("http://hansiv4.ddns.net:3000/")
                .post(myRequest)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }


        //String url = "http://hansiv4.ddns.net:3000/user/"+name.getText()+"/"+password.getText();
        //Log.i("DIM",url);

        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
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

        queue.add(jsonObjectRequest);*/
    }
}
