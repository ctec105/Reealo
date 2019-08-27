package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.reealo.R;

import static com.example.reealo.R.*;

public class DetalleProductoActivity extends AppCompatActivity {

    // Cremos e iniciamos el activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detalle_producto);

        // Cambiar el titulo del menu dinamicamente
        this.setTitle(R.string.titulo_detalle_producto);

        // Mostrar botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(id.textViewNombre);
        TextView precio = (TextView) findViewById(id.textViewPrecio);
        TextView precioOferta = (TextView) findViewById(id.textViewPrecioOferta);

        // recuperamos los valores que cargamos a la actividad al seleccionar un producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nombre.setText(bundle.getString("nombre"));
            precio.setText(bundle.getString("precio"));
            precioOferta.setText(bundle.getString("precioOferta"));
        }
    }

    // Inicializamos el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


}
