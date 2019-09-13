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

import com.example.reealo.Actividades.GestionarPromocionActivity;
import com.example.reealo.Adaptadores.PromocionAdapter;
import com.example.reealo.Clases.Promocion;
import com.example.reealo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Promociones extends Fragment {

    // TODO: Instancias que vamos a necesitar
    private List<Promocion> promocionList = new ArrayList<>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private PromocionAdapter promocionAdapter;

    // TODO: Constructor
    public Promociones() {

    }

    // TODO: Método que llena promociones a la lista
    private void llenarListaPromociones() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        Promocion promocion = new Promocion(datosImg[0],"100% peruano","Taza pequeña, gorro y toma todo con el 30% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
        promocion = new Promocion(datosImg[1],"Día del padre","Taza y gorro con el 25% de descuento");
        promocionList.add(promocion);
    }

    // TODO: Método que crea y devuelve la jerarquía de vistas asociadas con el fragmento
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // infla el diseño del fragmento promociones
        View vista =  inflater.inflate(R.layout.fragment_promociones, container, false);

        // llenamos el recyclerView de promociones
        recyclerView = vista.findViewById(R.id.recyclerviewPromociones);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarListaPromociones();
        promocionAdapter = new PromocionAdapter(promocionList);
        recyclerView.setAdapter(promocionAdapter);

        // si hizo clic en un elemento del recyclerView
        promocionAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mostramos un mensaje en pantalla con el nombre de la promocion seleccionada
                Toast.makeText(getActivity(), "Mensaje: " + promocionList.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();

                // invocamos al detalle de la promocion seleccionada y le pasamos algunos datos a la actividad
                Intent intent = new Intent(view.getContext(), GestionarPromocionActivity.class);
                intent.putExtra("nombre", promocionList.get(recyclerView.getChildAdapterPosition(view)).getNombre());
                intent.putExtra("descripcion", promocionList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
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
                if (dy > 0 || dy < 0 && fab.isShown())
                {
                    // ocultamos el botón flotante de agregar promociones
                    fab.hide();
                }
            }
            // cuando ya no hay un scroll
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    // mostramos el botón flotante de agregar promociones
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        // si hizo clic en el botón agregar promociones (FloatingActionButton)
        fab = vista.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // invocamos a la atividad registrar promociones
                Intent intent = new Intent(view.getContext(), GestionarPromocionActivity.class);
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