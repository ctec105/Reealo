package com.example.reealo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.example.reealo.Clases.Producto;
import com.example.reealo.fragmentos.Carrito;
import com.example.reealo.fragmentos.Notificaciones;
import com.example.reealo.fragmentos.Productos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.View;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Cargar el FloatingActionButton (es el icono de mensaje)
        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        // Cargar DrawerLayout
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        // cargar productos por defecto
        Fragment fragment = new Productos();
        callFragment(fragment);
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

    // Inicializar el contenido del menú de opciones estándar de la Actividad.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Se llama cuando se selecciona una opción del menú[
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemCarrito) {
            Toast.makeText(this, "Selecionaste menu Carrito", Toast.LENGTH_SHORT).show();
            // Cambiar el titulo del menu dinamicamente
            this.setTitle(R.string.titulo_carrito_compras);
            // cargamos el fragmento Carrito de Compras
            Fragment fragment = new Carrito();
            callFragment(fragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Se llama cuando se selecciona un elemento en el menú de navegación.
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        // si el id del item es igual al item seleccionado, entonces...
        if (id == R.id.nav_inicio) {
            // Cambiar el titulo del menu dinamicamente
            this.setTitle(R.string.titulo_buscar_producto);
            // cargamos el fragmento Productos
            fragment = new Productos();
            callFragment(fragment);
        } else if (id == R.id.nav_notificacion) {
            // cargamos el fragmento Notificaciones
            fragment = new Notificaciones();
            callFragment(fragment);
        } else if (id == R.id.nav_carrito) {
            // Cambiar el titulo del menu dinamicamente
            this.setTitle(R.string.titulo_carrito_compras);
            // cargamos el fragmento Carrito de Compras
            fragment = new Carrito();
            callFragment(fragment);
        } else if (id == R.id.nav_pedidos) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

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
