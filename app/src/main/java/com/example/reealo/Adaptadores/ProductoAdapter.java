package com.example.reealo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.R;

import java.util.List;

//import com.example.reealo.Clases.Productoss;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.MyViewHolder> implements View.OnClickListener {

    // TODO: Instancias que vamos a necesitar
    private List<ProductoTest> productoList;
    private View.OnClickListener listener;
    private Context context;

    // TODO: Constructor
    public ProductoAdapter(List<ProductoTest> productoList) {
        this.productoList = productoList;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // TODO: Enlazamos los controles de la vista
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

    // TODO: Creamos el RecyclerView para la lista de productos
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_producto, parent, false);
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }

    // TODO: Llenamos los controles con data proveniente de la lista que entra en el constructor
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ProductoTest producto = productoList.get(position);

        //Picasso.with(context).load("http://192.168.1.46/Publicaciones/imagenes/"+ producto.getImagen()).resize(400,400).into(holder.imagen);
        holder.imagen.setImageResource(producto.getIdImagen());
        holder.nombre.setText(producto.getNombre());
        holder.descripcion.setText("desscripcion del producto");
    }

    // TODO: Devuelve el número total de elementos en el conjunto de datos que posee el adaptador
    @Override
    public int getItemCount() {
        return productoList.size();
    }

    // TODO: Implementar el método onClick para el RecyclerView
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

}
