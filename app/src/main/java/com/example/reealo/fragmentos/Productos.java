package com.example.reealo.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Actividades.GestionarProductoActivity;
import com.example.reealo.Adaptadores.ProductoAdapter;
import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Productos extends Fragment {

    // TODO: Instancias que vamos a necesitar
    private List<ProductoTest> productoList = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private ProductoAdapter productoAdapter;

    // TODO: Constructur
    public Productos() {

    }

    // TODO: Metodo que llena productos a la lista
    private void llenarListaProductos() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        ProductoTest producto = new ProductoTest(datosImg[0],"Cojin Blanco","Quedan 10","S/. 20.00","S/. 15.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[1],"Cojin mamá","Quedan 9","S/. 25.00","S/. 20.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[2],"Cojin Rosado","Quedan 11","S/. 15.00","S/. 10.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[3],"Llavero reina","Quedan 15","S/. 8.00","S/. 5.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[4],"Llavero mamá","Quedan 5","S/. 7.00","S/. 6.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);

        producto =  new ProductoTest(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);
    }

    // TODO: Metodo que crea y devuelve la jerarquía de vistas asociada con el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // inflar el diseño del fragmento productos
        View vista =  inflater.inflate(R.layout.fragment_productos, container, false);

        // llenamos el recyclerView de productos
        recyclerView = vista.findViewById(R.id.recyclerviewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaProductos();
        productoAdapter = new ProductoAdapter(productoList);
        recyclerView.setAdapter(productoAdapter);

        // si hizo clic en un elemento del recyclerView, recuperamos los datos
        productoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mostrar un mensaje con el nombre del producto seleccionado
                Toast.makeText(getActivity(), "Mensaje: " + productoList.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

                // invocamos al detalle del producto seleccionado y le pasamos algunos datos a la actividad
                Intent intent = new Intent(view.getContext(), GestionarProductoActivity.class);
                intent.putExtra("nombre", productoList.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                intent.putExtra("descripcion", productoList.get(recyclerView.getChildAdapterPosition(view)).getNombre()); // SETEAR DESC
                view.getContext().startActivity(intent);
            }
        });

        // si hizo un scroll en el recyclerView
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            // cuando ocurra un scroll
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                // si hubo un desplazamiento (arriba/abajo)
                if (dy > 0 || dy < 0 && floatingActionButton.isShown())
                {
                    // ocultamos el botón flotante de agregar promociones
                    floatingActionButton.hide();
                }
            }
            // cuando ya no hay un scroll
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    // mostramos el botón flotante de agregar promociones
                    floatingActionButton.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        // si hizo clic en el botón agregar promociones (FloatingActionButton)
        floatingActionButton = vista.findViewById(R.id.fabProducto);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // invocamos a la atividad registrar productos
                Intent intent = new Intent(view.getContext(), GestionarProductoActivity.class);
                view.getContext().startActivity(intent);
            }
        });


        return vista;
    }

    // TODO: Prepara el menú de opciones antes de que se muestre el menú (usado para deshabilitar o habilitar elementos)
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // quiero ocultar la opciòn del carrito de compras
        MenuItem item = menu.findItem(R.id.itemCarrito);
        item.setVisible(false);
    }

}