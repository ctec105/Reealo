package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.reealo.R;

public class GestionarProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_producto);

        // Cambiar el titulo del menu dinamicamente
        this.setTitle(R.string.titulo_registrar_producto);

        // Mostrar botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionProducto);

        // recuperamos los valores que cargamos a la actividad al seleccionar una producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nombre.setText(bundle.getString("nombre"));
            descripcion.setText(bundle.getString("descripcion"));
        }
    }
}
