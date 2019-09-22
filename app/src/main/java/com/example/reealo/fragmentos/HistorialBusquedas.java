package com.example.reealo.fragmentos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.reealo.R;
import com.example.reealo.sqllite.DAOException;
import com.example.reealo.sqllite.Historial;
import com.example.reealo.sqllite.HistorialDAO;

import java.util.ArrayList;

public class HistorialBusquedas extends Fragment {

    public HistorialBusquedas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // inflamos el layout para este fragmento
        View vista = inflater.inflate(R.layout.fragment_historial_busquedas, container, false);
        buscar(vista);

        return vista;
    }

    public void buscar(View view) {
        //EditText criterio = (EditText) findViewById(R.id.criterio);
        final HistorialDAO dao = new HistorialDAO(getActivity().getBaseContext());
        try {
            // invocamos al metodo buscar historial que trae datos de la bd sqllite
            final ArrayList<Historial> historialList = dao.buscar("");//riterio.getText().toString()

            // lo convertirmos en un lista de Strings
            final ArrayList<String> encontrados = new ArrayList<String>();
            for (Historial gm : historialList) {
                encontrados.add(gm.getBusqueda() + " - " + gm.getFecha());
            }

            // llenamos el adaptador
            final ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    encontrados);

            // lleanmos el ListView
            final ListView listaResultados = (ListView) view.findViewById(R.id.listVieHistorialBusquedas);
            listaResultados.setAdapter(adaptador);

            // si hizo clic en un item del ListView
            listaResultados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Toast.makeText(getContext(), "Selecionaste un item" + adaptador.getItem(position), Toast.LENGTH_SHORT).show();
                }
            });

            // si mantiene presionado un item del ListView
            listaResultados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                    final int which_item = position;
                    Log.i("which_item", which_item + "");

                    new AlertDialog.Builder(getContext())
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("¿Deseas borrar esta busqueda de tu historial?")
                            .setMessage(Html.fromHtml("Ya hiciste esta busqueda. Si borras <b>" + historialList.get(which_item).getBusqueda()
                                    + "</b> de tu historial, se quitará permanentemente."))
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {

                                    // eliminamos el registro de la bd sqllite
                                    try {
                                        dao.eliminar(historialList.get(which_item).getId());
                                        Toast.makeText(getContext(), "Se eliminó correctamente", Toast.LENGTH_SHORT).show();
                                    } catch (DAOException e) {
                                        e.printStackTrace();
                                    }

                                    // eliminamos el item seleccionado
                                    encontrados.remove(which_item);
                                    adaptador.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                    return true;
                }
            });


        } catch (DAOException e) {
            Log.i("HistorialBusquedas", "====> " + e.getMessage());
        }
    }


    // TODO: Prepara el menú de opciones antes de que se muestre el menú (usado para deshabilitar o habilitar elementos)
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // quiero ocultar la opciòn del carrito de compras
        MenuItem itemCarrito = menu.findItem(R.id.itemCarrito);
        MenuItem itemBuscar = menu.findItem(R.id.itemProductos);
        itemCarrito.setVisible(false);
        itemBuscar.setVisible(false);
    }

}
