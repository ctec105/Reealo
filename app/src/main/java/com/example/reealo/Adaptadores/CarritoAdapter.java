package com.example.reealo.Adaptadores;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

//import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.Clases.Producto;
import com.example.reealo.R;
import com.example.reealo.fragmentos.Carrito;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.MyViewHolder> implements View.OnClickListener {

    private List<Producto> productoList;
    private View.OnClickListener listener;
    private Context context;
    private String codProd;

    // Obtenemos los valores de la vista
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgProducto;
        private TextView lblDescripcion, lblPrecio, lblPrecioOferta;
        private Spinner cboCantidad;

        private SharedPreferences carrito;
        private Gson gson = new Gson();


        public MyViewHolder(View view) {
            super(view);
            imgProducto = (ImageView) view.findViewById(R.id.imageViewProducto);
            lblDescripcion = (TextView) view.findViewById(R.id.lblDescProdCarrito);
            lblPrecio = (TextView) view.findViewById(R.id.lblPrecioCarrito);
            lblPrecioOferta = (TextView) view.findViewById(R.id.lblPrecioOfertaCarrito);
            cboCantidad = (Spinner) view.findViewById(R.id.cboCantProdCarrito);
        }
    }

    @Override
    public CarritoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_carrito, parent, false);

        itemView.setOnClickListener(this);

        return new CarritoAdapter.MyViewHolder(itemView);
    }

    public CarritoAdapter(List<Producto> productoList, Context context) {
        this.productoList = productoList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(CarritoAdapter.MyViewHolder holder, int position) {
//        Producto producto = productoList.get(position);
//        holder.imgProducto.setImageResource(Integer.parseInt(producto.getImagen()));
//        holder.lblDescripcion.setText(producto.getDescripcion());
//        holder.lblPrecio.setText("S/. " + producto.getPrecioTotal());
//        holder.lblPrecio.setPaintFlags(holder.lblPrecio.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // va a tachar el texto
//        holder.lblPrecioOferta.setText("S/. " + producto.getPrecioOfertaTotal());
//        holder.cboCantidad.setSelection(producto.getCantidad());

        Producto producto = productoList.get(position);
        codProd = producto.getCodigo();
        //holder.imgProducto.setImageResource(Integer.parseInt(producto.getImagen()));
        Picasso.with(context).load("http://josel.jl.serv.net.mx/ROOT-160/imagenes/"+ producto.getImagen()).resize(400,400).into(holder.imgProducto);
        holder.lblDescripcion.setText(producto.getDescripcion());
        holder.lblPrecio.setText("S/. " + producto.getPrecio() * producto.getCantidad());
        holder.lblPrecio.setPaintFlags(holder.lblPrecio.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // va a tachar el texto
        holder.lblPrecioOferta.setText("S/. " + producto.getPrecioOferta() * producto.getCantidad());
        holder.cboCantidad.setSelection(producto.getCantidad());
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    // Implementar el método onClick para el RecyclerView
    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
