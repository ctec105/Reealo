package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.reealo.R;

public class RegistrarClienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        // Cambiar el titulo del menu dinamicamente
        this.setTitle(R.string.titulo_registrar_cliente);

        // Mostrar botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
