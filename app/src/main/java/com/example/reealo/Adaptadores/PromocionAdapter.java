package com.example.reealo.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Clases.Promocion;
import com.example.reealo.R;

import java.util.List;

public class PromocionAdapter extends RecyclerView.Adapter<PromocionAdapter.MyViewHolder> implements View.OnClickListener {

    // Instanciamos
    private List<Promocion> promocionList;
    private View.OnClickListener listener;

    // Constructor
    public PromocionAdapter(List<Promocion> promocionList) {
        this.promocionList = promocionList;
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

    // Creamos el RecyclerView para la lista de promociones
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_promociones, parent, false);
        itemView.setOnClickListener(this);
        return new PromocionAdapter.MyViewHolder(itemView);
    }

    // Actualiza el contenido de RecyclerView.ViewHolder con el elemento en la posición dada
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Promocion promocion = promocionList.get(position);
        holder.imagen.setImageResource(promocion.getImagen());
        holder.nombre.setText(promocion.getNombre());
        holder.descripcion.setText(promocion.getDescripcion());
    }

    // Devuelve el número total de elementos en el conjunto de datos que posee el adaptador.
    @Override
    public int getItemCount() {
        return promocionList.size();
    }

    // Implementar el método onClick para el RecyclerView
    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
    }

}
