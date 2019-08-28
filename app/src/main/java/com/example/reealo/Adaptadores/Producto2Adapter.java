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

public class Producto2Adapter extends RecyclerView.Adapter<Producto2Adapter.MyViewHolder> implements View.OnClickListener {

    // Instanciamos
    private List<Producto> productoList;
    private View.OnClickListener listener;

    // Constructor
    public Producto2Adapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // Obtenemos los valores de la vista
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView nombre, descripcion;

        public MyViewHolder(View view) {
            super(view);
            imagen = (ImageView) view.findViewById(R.id.imageViewProducto);
            nombre = (TextView) view.findViewById(R.id.lblNombreProducto);
            descripcion = (TextView) view.findViewById(R.id.lblDescripcionProducto);
        }
    }

    // Creamos el RecyclerView para la lista de productos 2
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_2_fila, parent, false);
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }

    // Actualiza el contenido de RecyclerView.ViewHolder con el elemento en la posición dada
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        holder.imagen.setImageResource(producto.getIdImagen());
        holder.nombre.setText(producto.getNombre());
        holder.descripcion.setText("desscripcion del producto"); // falta atributo descripcion
    }

    // Devuelve el número total de elementos en el conjunto de datos que posee el adaptador.
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

}
