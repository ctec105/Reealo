package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.Clases.Producto;
//import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import static com.example.reealo.R.*;

public class DetalleCatalogoActivity extends AppCompatActivity {

    // Declaramos las variables globales para la GUI
    ImageView imgDetalle;
    TextView lblDescripcion, lblDetalle, lblPrecio, lblPrecioOferta;
    Button btnAgregar;

    // Declaramos variables globales para recibir los valores
    String img = "", cod = "", desc = "", detalle = "";
    double precio = 0, precioOferta = 0;

    // Variables globales que vamos a necesitar
    ArrayList<Producto> cesta = new ArrayList<Producto>();
    SharedPreferences carrito;
    Gson gson = new Gson();

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
        imgDetalle = (ImageView) findViewById(id.imgDetalleCatalogo);
        lblDescripcion = (TextView) findViewById(id.lblDescripcionDetalleCatalogo);
        lblDetalle = (TextView) findViewById(id.lblDetalleDetalleCatalogo);
        lblPrecio = (TextView) findViewById(id.lblPrecioDetalleCatalogo);
        //lblPrecioOferta = (TextView) findViewById(id.lblPrecioOfertaDetalleCatalogo);
        btnAgregar = findViewById(id.btnAgregarDetalleCatalogo);

        // recuperamos los valores que cargamos a la actividad al seleccionar un producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            img = bundle.getString("imagen");
            cod = bundle.getString("codigo");
            desc = bundle.getString("descripcion");
            detalle = bundle.getString("detalle");
            precio = bundle.getDouble("precio");
            //precioOferta = bundle.getDouble("precioOferta");
        }

        // seteamos los valores a la vista
        //imgDetalle.setImageResource(Integer.parseInt(img));
        Picasso.with(getApplicationContext()).load("http://josel.jl.serv.net.mx/ROOT-160/imagenes/"+ img).into(imgDetalle);
        lblDescripcion.setText(desc);
        lblDetalle.setText(detalle);
        lblPrecio.setText("S/. " + precio);
        //lblPrecio.setPaintFlags(lblPrecio.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // va a tachar el texto
        //lblPrecioOferta.setText("S/. " + precioOferta);

        // recuperando datos de la preferencia
        File file = new File("/data/data/" + getPackageName() + "/shared_prefs/carrito.xml");
        carrito = getSharedPreferences("carrito", MODE_PRIVATE);
        // si el archivo fisico existe
        if (file.exists()) {
            String guardado = carrito.getString("cesta", "");// valor por defecto si no lo encuentra
            Type type = new TypeToken<ArrayList<Producto>>() {
            }.getType();
            cesta = gson.fromJson(guardado, type);
        }

        // si hizo clic en el botón agregar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Producto producto = new Producto(cod, desc, precio, precioOferta, img, 1);
                if (agregarProducto(producto) == true) {
                    Toast.makeText(getApplicationContext(), "Se aumento la cantidad", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Se agregó nuevo producto", Toast.LENGTH_SHORT).show();
                }

                // convertimos la lista en un JSON y lo guardamos en un String
                String listaGson = gson.toJson(cesta);

                // creamos el xml carrito en modo privado (si no existe la preferencia va a crear y si existe la va a actualizar)
                carrito = getSharedPreferences("carrito", MODE_PRIVATE);

                // creamos una variable de tipo editor y le decimos que va a almacenar nuevos datos
                SharedPreferences.Editor editor = carrito.edit();

                // le decimos que va a almacenar una lista Json
                editor.putString("cesta", listaGson);

                // escribimos y cerramos
                editor.commit();
            }
        });

    }

    // TODO: Inicializa el contenido del menú superior de la actividad
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // TODO: Prepara el menú de opciones antes de que se muestre el menú (usado para deshabilitar o habilitar elementos)
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(id.itemProductos);
        item.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    // TODO: Se llama cuando se selecciona una opción del menú superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.itemCarrito) {
            // mostramos un mensaje en pantalla
            Toast.makeText(this, "Has pulsado el carrito de compras", Toast.LENGTH_SHORT).show();

            // recuperando datos de la cesta
            File file = new File("/data/data/" + getPackageName() + "/shared_prefs/carrito.xml");

            // si existe el carrito
            if (file.exists()) {
                // le decimos a la actividad principal que queremos cargar el fragmento de carrito de compras
                MainActivity.opcion = 3;
                // invocamos a la actividad principal
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                // mostramos un mensaje en pantalla
                Toast.makeText(this, "Cesta vacia", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else if (id == android.R.id.home) {
            // mostramos un mensaje en pantalla
            Toast.makeText(this, "Has pulsado la flecha atrás", Toast.LENGTH_LONG).show();
            // le decimos a la actividad principal que queremos cargar el fragmento de catalogo
            MainActivity.opcion = 1;
            // invocamos a la actividad principal
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Creamos el método para agregar pruductos a la cesta
    private boolean agregarProducto(Producto producto) {
        for (int i = 0; i < cesta.size(); i++) {
            // si el producto ya existe en la cesta
            if (cesta.get(i).getCodigo().equals(producto.getCodigo())) {
                cesta.get(i).setCantidad(cesta.get(i).getCantidad() + 1);
                return true;
            }
        }
        // si el producto no existe, lo agregamos a la cesta
        cesta.add(producto);
        return false;
    }

}
