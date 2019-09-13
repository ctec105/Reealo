package com.example.reealo;

import android.os.Bundle;
import com.example.reealo.fragmentos.Carrito;
import com.example.reealo.fragmentos.Catalogo;
import com.example.reealo.fragmentos.Notificaciones;
import com.example.reealo.fragmentos.Productos;
import com.example.reealo.fragmentos.Promociones;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    // TODO: Esta opción va a permitir pasar de otra actividad a cualquiera de los fragmentos del MainActivity
    public static int opcion;

    // TODO: Crea e inicia la actividad principal (MainActivity)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Cargamos la barra de herramienta que va en la parte superior (Toolbar)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 2. Cargamos el panel lateral que va al lado izquierdo (DrawerLayout)
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // 3. Para cuando llamen a los fragmentos de la actividad prinicpal (cargamos el catálogo de productos por defecto)
        switch (opcion) {
            case 1:
                callFragment(new Catalogo());
                break;
            case 2:
                callFragment(new Promociones());
                this.setTitle(R.string.titulo_listar_promocion);
                break;
            case 3:
                callFragment(new Carrito());
                this.setTitle(R.string.titulo_carrito_compras);
                break;
            case 4:
                callFragment(new Productos());
                this.setTitle(R.string.titulo_listar_producto);
                break;
            default:
                callFragment(new Catalogo());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
        if (id == R.id.itemCarrito) {
            // mostramos un mensaje en pantalla
            Toast.makeText(this, "Has pulsado el carrito de compras", Toast.LENGTH_SHORT).show();
            // Cambiamos el titulo de la actividad
            this.setTitle(R.string.titulo_carrito_compras);
            // cargamos el fragmento de carrito de Compras
            Fragment fragment = new Carrito();
            callFragment(fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // TODO: Se llama cuando se selecciona un elemento en el menú de navegación del panel lateral
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_inicio) {
            // cambiamos el título de la actividad
            this.setTitle(R.string.titulo_buscar_producto);
            // cargamos el fragmento de Catálogo de Productos
            fragment = new Catalogo();
            callFragment(fragment);
        } else if (id == R.id.nav_productos) {
            // cambiamos el título de la actividad
            this.setTitle(R.string.titulo_listar_producto);
              // cargamos el fragmento de Productos
            fragment = new Productos();
            callFragment(fragment);
        } else if (id == R.id.nav_promociones) {
            // cambiamos el título de la actividad
            this.setTitle(R.string.titulo_listar_promocion);
            // cargamos el fragmento de Promociones
            fragment = new Promociones();
            callFragment(fragment);
        } else if (id == R.id.nav_notificacion) {
            // cambiamos el título de la actividad
            this.setTitle(R.string.titulo_listar_notificacion);
            // cargamos el fragmento de Notificaciones
            fragment = new Notificaciones();
            callFragment(fragment);
        } else if (id == R.id.nav_carrito) {
            // cambiamos el título de la actividad
            this.setTitle(R.string.titulo_carrito_compras);
            // cargamos el fragmento de Carrito de Compras
            fragment = new Carrito();
            callFragment(fragment);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        // cerramos el panel lateral que va al lado izquierdo (DrawerLayout)
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Método creado para llamar a los fragmentos (reutilizable)
    void callFragment (Fragment fragmenet){
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.contenedor_principal,fragmenet).commit();
            DrawerLayout driver = (DrawerLayout) findViewById(R.id.drawer_layout);
            driver.closeDrawer(GravityCompat.START);
        }catch (Exception ex){
            Toast.makeText(this,"",Toast.LENGTH_LONG);
        }

    }

}