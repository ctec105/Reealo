package com.example.reealo.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.reealo.MainActivity;
import com.example.reealo.R;

public class LogueoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);
    }


    public void iniciarSesion(View v){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
