package com.example.reealo.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.reealo.MainActivity;
import com.example.reealo.R;

public class GestionarPromocionActivity extends AppCompatActivity {

    // TODO: Crea e inicia el activity (GestionarPromocionActivity)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_promocion);

        // activamos el botón de retroceso en el menú superior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionPromocion);
        Button button = (Button)findViewById(R.id.btnGrabar);

        // recuperamos los valores que cargamos a la actividad al seleccionar una promocion
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.setTitle(R.string.titulo_actualizar_promocion);
            button.setText(R.string.button_actualizar);
            nombre.setText(bundle.getString("nombre"));
            descripcion.setText(bundle.getString("descripcion"));
        } else {
            this.setTitle(R.string.titulo_crear_promocion);
            button.setText(R.string.button_registrar);
        }

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
            // le decimos a la actividad principal que queremos cargar el fragmento de promociones
            MainActivity.opcion = 2;
            // invocamos a la actividad principal
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}