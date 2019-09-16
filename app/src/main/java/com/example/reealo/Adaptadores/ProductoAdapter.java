package com.example.reealo.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Clases.Producto;
import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

//import com.example.reealo.Clases.Productoss;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.MyViewHolder> implements View.OnClickListener {

    // TODO: Instancias que vamos a necesitar
    private List<Producto> productoList;
    private View.OnClickListener listener;
    private Context context;

    // TODO: Constructor
    public ProductoAdapter(List<Producto> productoList, Context context) {
        this.productoList = productoList;
        this.context = context;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // TODO: Enlazamos los controles de la vista
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imagen;
        public TextView nombre, descripcion,precio,stock;

        public MyViewHolder(View view) {
            super(view);
            imagen = (ImageView) view.findViewById(R.id.imageViewProducto);
            nombre = (TextView) view.findViewById(R.id.lblNombreProducto);
            descripcion = (TextView) view.findViewById(R.id.lblDescripcionProducto);
            precio = (TextView) view.findViewById(R.id.lblPrecio);
            stock =  (TextView) view.findViewById(R.id.lblStock);
        }
    }

    // TODO: Creamos el RecyclerView para la lista de productos
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_producto, parent, false);
        //itemView.setOnClickListener(this);
        //return new MyViewHolder(itemView);

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_producto, parent, false);
        itemView.setOnClickListener(this);
        return new ProductoAdapter.MyViewHolder(itemView);
    }

    // TODO: Llenamos los controles con data proveniente de la lista que entra en el constructor
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //ProductoTest producto = productoList.get(position);
        //Picasso.with(context).load("http://192.168.1.46/Publicaciones/imagenes/"+ producto.getImagen()).resize(400,400).into(holder.imagen);
        //holder.imagen.setImageResource(producto.getIdImagen());
        //holder.nombre.setText(producto.getNombre());
        //holder.descripcion.setText("desscripcion del producto");

        Producto producto = productoList.get(position);
        //holder.imagen.setImageResource(Integer.parseInt(producto.getImagen()));
        Picasso.with(context).load("http://josel.jl.serv.net.mx/ROOT-160/imagenes/"+ producto.getImagen()).resize(400,400).into(holder.imagen);
        //holder.stock.setText("Quedan " + String.valueOf(producto.getStock()) + " unidades");
        //holder.precio.setText("S/. " + producto.getPrecio());
        //holder.precioOerta.setText(String.valueOf(producto.getPrecioOferta()));
        holder.nombre.setText(String.valueOf(producto.getDescripcion()));
        holder.descripcion.setText(String.valueOf(producto.getDetalle()));
        holder.precio.setText("S/. " +String.valueOf(producto.getPrecio()));
        holder.stock.setText("Quedan " +String.valueOf(producto.getStock()));


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
