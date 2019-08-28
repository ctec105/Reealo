package com.example.reealo.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.reealo.Actividades.DetalleProductoActivity;
import com.example.reealo.Actividades.GestionarProductoActivity;
import com.example.reealo.Actividades.GestionarPromocionActivity;
import com.example.reealo.Adaptadores.Producto2Adapter;
import com.example.reealo.Clases.Producto;
import com.example.reealo.R;

import java.util.ArrayList;
import java.util.List;

public class Productos2 extends Fragment {

    // Instancias que vamos a necesitar
    private List<Producto> productoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Producto2Adapter producto2Adapter;

    // Constructur
    public Productos2() {

    }

    // Metodo que llena productos a la lista
    private void llenarListaProductos() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        Producto producto = new Producto(datosImg[0],"Cojin Blanco","Quedan 10","S/. 20.00","S/. 15.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[1],"Cojin mamá","Quedan 9","S/. 25.00","S/. 20.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[2],"Cojin Rosado","Quedan 11","S/. 15.00","S/. 10.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[3],"Llavero reina","Quedan 15","S/. 8.00","S/. 5.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[4],"Llavero mamá","Quedan 5","S/. 7.00","S/. 6.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[5],"Vaso mamá","Quedan 13","S/. 15.00","S/.11.00");
        productoList.add(producto);
    }

    // Metodo que crea y devuelve la jerarquía de vistas asociada con el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflar el diseño del fragmento productos
        View vista =  inflater.inflate(R.layout.fragment_productos, container, false);

        // ;;enamos el recyclerView de productos
        recyclerView = vista.findViewById(R.id.recyclerviewProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaProductos();
        producto2Adapter = new Producto2Adapter(productoList);
        recyclerView.setAdapter(producto2Adapter);

        // si hizo clic en un elemento del recyclerView, recuperamos los datos
        producto2Adapter.setOnClickListener(new View.OnClickListener() {
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

        return vista;
    }

}
