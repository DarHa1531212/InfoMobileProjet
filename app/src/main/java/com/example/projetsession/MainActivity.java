package com.example.projetsession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //idconfirmbutton

        //idConfirm();
    }

    public void idConfirm(View v){
        Button idButton = (Button) v;
        Log.i("DIM", "button click");
        TextView name = findViewById(R.id.idName);
        Log.i("DIM",String.valueOf( name.getText()));
        TextView password = findViewById(R.id.password);
        Log.i("DIM",String.valueOf( password.getText()));


    }

}