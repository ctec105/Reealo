package com.example.reealo.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.reealo.Actividades.DetalleProductoActivity;
import com.example.reealo.Adaptadores.CarritoAdapter;
import com.example.reealo.Adaptadores.ProductoAdapter;
import com.example.reealo.Clases.Producto;
import com.example.reealo.R;

import java.util.ArrayList;
import java.util.List;

public class Carrito extends Fragment {

    private List<Producto> productoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarritoAdapter carritoAdapter;

    public Carrito() {
    }

    private void llenarListaProductos() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        Producto producto = new Producto(datosImg[0],"Cojin Blanco","Quedan 10","S/. 20.00","S/. 15.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[1],"Cojin mamá","Quedan 9","S/. 25.00","S/. 20.00");
        productoList.add(producto);

        producto =  new Producto(datosImg[2],"Cojin Rosado","Quedan 11","S/. 15.00","S/. 10.00");
        productoList.add(producto);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =  inflater.inflate(R.layout.fragment_carrito, container, false);

        recyclerView = vista.findViewById(R.id.recyclerviewCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaProductos();

        carritoAdapter = new CarritoAdapter(productoList);
        recyclerView.setAdapter(carritoAdapter);


        carritoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Mensaje: " + productoList.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), DetalleProductoActivity.class);
                intent.putExtra("nombre", productoList.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                intent.putExtra("precio", productoList.get(recyclerView.getChildAdapterPosition(view)).getPrecio());
                intent.putExtra("precioOferta", productoList.get(recyclerView.getChildAdapterPosition(view)).getPrecioOferta());
                view.getContext().startActivity(intent);
            }
        });

        return vista;
    }

}
