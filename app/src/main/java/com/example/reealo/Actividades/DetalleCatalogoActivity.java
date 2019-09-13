package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.MainActivity;
import com.example.reealo.R;

import static com.example.reealo.R.*;

public class DetalleCatalogoActivity extends AppCompatActivity {

    // TODO: Crea e inicia el activity (DetalleProductoActivity)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_detalle_producto);

        // cambiamos el título de la actividad
        this.setTitle(string.titulo_detalle_producto);

        // activamos el botón de retroceso en el menú superior
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(id.textViewNombre);
        TextView precio = (TextView) findViewById(id.lnkEditar);
        TextView precioOferta = (TextView) findViewById(id.lnkEliminar);

        // recuperamos los valores que cargamos a la actividad al seleccionar un producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            nombre.setText(bundle.getString("nombre"));
            precio.setText(bundle.getString("precio"));
            precioOferta.setText(bundle.getString("precioOferta"));
        }

    }

    // TODO: Inicializa el contenido del menú superior de la actividad
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // TODO: Se llama cuando se selecciona una opción del menú superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.itemCarrito) {
            // mostramos un mensaje en pantalla
            Toast.makeText(this, "Has pulsado el carrito de compras", Toast.LENGTH_SHORT).show();
            // le decimos a la actividad principal que queremos cargar el fragmento de carrito de compras
            MainActivity.opcion = 3;
            // invocamos a la actividad principal
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == android.R.id.home){
            // mostramos un mensaje en pantalla
            Toast.makeText(this,"Has pulsado la flecha atrás", Toast.LENGTH_LONG).show();
            // le decimos a la actividad principal que queremos cargar el fragmento de catalogo
            MainActivity.opcion = 1;
            // invocamos a la actividad principal
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
