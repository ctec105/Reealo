package com.example.reealo.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Actividades.GestionarPromocionActivity;
import com.example.reealo.Adaptadores.PromocionAdapter;
import com.example.reealo.Clases.Promocion;
import com.example.reealo.R;

import java.util.ArrayList;
import java.util.List;

public class Promociones extends Fragment {

    // Instancias que vamos a necesitar
    private List<Promocion> promocionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PromocionAdapter promocionAdapter;

    // Constructur
    public Promociones() {

    }

    // Metodo que llena promociones a la lista
    private void llenarListaPromociones() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        Promocion promocion = new Promocion(datosImg[0],"100% peruano","Taza pequeña, gorro y toma todo con el 30% de descuento");
        promocionList.add(promocion);

        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
    }

    // Metodo que crea y devuelve la jerarquía de vistas asociada con el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflar el diseño del fragmento promociones
        View vista =  inflater.inflate(R.layout.fragment_promociones, container, false);

        // ;;enamos el recyclerView de promociones
        recyclerView = vista.findViewById(R.id.recyclerviewPromociones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaPromociones();
        promocionAdapter = new PromocionAdapter(promocionList);
        recyclerView.setAdapter(promocionAdapter);

        // si hizo clic en un elemento del recyclerView, recuperamos los datos
        promocionAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mostrar un mensaje con el nombre de la promocion seleccionada
                Toast.makeText(getActivity(), "Mensaje: " + promocionList.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

                // invocamos al detalle de la promocion seleccionada y le pasamos algunos datos a la actividad
                Intent intent = new Intent(view.getContext(), GestionarPromocionActivity.class);
                intent.putExtra("nombre", promocionList.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                intent.putExtra("descripcion", promocionList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                view.getContext().startActivity(intent);
            }
        });

        return vista;
    }
}
