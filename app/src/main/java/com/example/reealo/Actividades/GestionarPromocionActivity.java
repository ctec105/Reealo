package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.reealo.R;

public class GestionarPromocionActivity extends AppCompatActivity {

    // Cremos e iniciamos el activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_promocion);

        // Cambiar el titulo del menu dinamicamente
        this.setTitle(R.string.titulo_crear_promocion);

        // Mostrar botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionPromocion);

        // recuperamos los valores que cargamos a la actividad al seleccionar una promocion
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nombre.setText(bundle.getString("nombre"));
            descripcion.setText(bundle.getString("descripcion"));
        }
    }

    // Inicializamos el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}
