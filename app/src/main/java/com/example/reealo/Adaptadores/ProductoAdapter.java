package com.example.reealo.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Clases.Producto;
import com.example.reealo.R;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Producto> productoList;
    private View.OnClickListener listener;

    // Obtenemos los valores de la vista
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public  ImageView imagen;
        public TextView nombre, stock, precio, precioOerta;

        public MyViewHolder(View view) {
            super(view);
            imagen = (ImageView) view.findViewById(R.id.imageViewProducto);
            nombre = (TextView) view.findViewById(R.id.textViewTitulo);
            stock = (TextView) view.findViewById(R.id.textViewStock);
            precio = (TextView) view.findViewById(R.id.textViewPrecio);
            precioOerta = (TextView) view.findViewById(R.id.textViewPrecioOferta);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productos_fila, parent, false);

        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    public ProductoAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        holder.imagen.setImageResource(producto.getIdImagen());
        holder.nombre.setText(producto.getNombre());
        holder.stock.setText(producto.getStock());
        holder.precio.setText(producto.getPrecio());
        holder.precioOerta.setText(producto.getPrecioOferta());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    // Implementar el método onClick para el RecyclerView
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }


}
