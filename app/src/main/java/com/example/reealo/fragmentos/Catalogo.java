package com.example.reealo.fragmentos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Actividades.DetalleCatalogoActivity;
import com.example.reealo.Adaptadores.CatalogoAdapter;
//import com.example.reealo.Clases.ProductoTest;
import com.example.reealo.Clases.Producto;
import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.example.reealo.sqllite.DAOException;
import com.example.reealo.sqllite.HistorialDAO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Catalogo extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    // TODO: Instancias que vamos a necesitar
    private List<Producto> productoList = new ArrayList<>();
    private RecyclerView recyclerView;
    //RecyclerView.Adapter catalogoAdapter;
    private CatalogoAdapter catalogoAdapter;

    // TODO: Constructur
    public Catalogo() {

    }

    // TODO: Metodo que llena productos a la lista
    /*private void llenarListaProductos() {
        int[] datosImg = {R.drawable.cojinblanco, R.drawable.cojinmama, R.drawable.cojinrosado, R.drawable.llaverareinamama, R.drawable.llaveromama, R.drawable.vasomama};

        Producto producto = new Producto("P0001","Cojin Blanco", "Cojín blanco mamá eres mi tesoro - Colección ocación mamá",10, 20.00, 15.00, String.valueOf(datosImg[0]));
        productoList.add(producto);

        producto =  new Producto("P0002","Cojin mamá","Cojín blanco mamá reyna - Colección ocación mamá",9,25.00,20.00,String.valueOf(datosImg[1]));
        productoList.add(producto);

        producto =  new Producto("P0003","Cojin Rosado","Cojín blanco wonderful mom - Colección ocación mamá",11,15.00,10.00, String.valueOf(datosImg[2]));
        productoList.add(producto);

        producto =  new Producto("P0004","Llavero reina","Cojín blanco wonderful mom - Colección ocación mamá",15,8.00,5.00, String.valueOf(datosImg[3]));
        productoList.add(producto);

        producto =  new Producto("P0005","Llavero mamá","Llavero reina total - Colección ocación mamá",5,7.00,6.00, String.valueOf(datosImg[4]));
        productoList.add(producto);

        producto =  new Producto("P00006","Vaso mamá","Llavero wonderful mom - Colección ocación mamá",13,15.00,11.00,String.valueOf(datosImg[5]));
        productoList.add(producto);
    }*/

    // TODO: Metodo que obtiene los productos del servicio y lo llena a la lista
    private void llenarListaProductos() {
        // creamos un objeto OkHttpClient par realizar llamadas eficientes de red
        OkHttpClient client = new OkHttpClient();

        // para usar OkHttp necesitamos crear un objeto Request
        Request request = new Request.Builder()
                .url("http://joselyn.jl.serv.net.mx/ROOT-160/webresources/testWS/listarProductos")
                .build();

        // para realizar una llamada de red síncrona, use Client para crear un Call object y use el método execute
        //Response response = client.newCall(request).execute();

        // Para realizar llamadas asincrónicas, cree un Call object pero use el método enqueue
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Codigo inesperado: " + response);
                } else {
                    // obtenemos la cadena json
                    String cadenaJson = response.body().string();
                    Log.i("cadenaJson ====>", cadenaJson);

                    // creamos el objeto Gson que se encargará de las conversiones
                    Gson gson = new Gson();

                    // creamos el tipo que represente el arreglo
                    Type listType = new TypeToken<ArrayList<Producto>>() {
                    }.getType();

                    // convertimos un array Json a ArrayList
                    productoList = gson.fromJson(cadenaJson, listType);

                    /*Nota: Si está utilizando Android y desea actualizar la interfaz de usuario, debe
                     * usar Content.runOnUiThread(new Runnable) para sincronizar con el hilo de la interfaz de usuario
                     * */
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                // le pasamos el listado de productos al adaptador encargado de llenar el recyclerView
                                catalogoAdapter = new CatalogoAdapter(productoList, getActivity().getApplicationContext());
                                recyclerView.setAdapter(catalogoAdapter);

                                // si hizo clic en un elemento del recyclerView, recuperamos los datos
                                catalogoAdapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // mostrar un mensaje con el nombre del producto seleccionado
                                        Toast.makeText(getActivity(), "Mensaje: " + productoList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion(), Toast.LENGTH_SHORT).show();

                                        // invocamos al detalle del producto seleccionado y le pasamos algunos datos a la actividad
                                        Intent intent = new Intent(view.getContext(), DetalleCatalogoActivity.class);
                                        intent.putExtra("codigo", productoList.get(recyclerView.getChildAdapterPosition(view)).getCodigo());
                                        intent.putExtra("descripcion", productoList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                                        intent.putExtra("detalle", productoList.get(recyclerView.getChildAdapterPosition(view)).getDetalle());
                                        intent.putExtra("precio", productoList.get(recyclerView.getChildAdapterPosition(view)).getPrecio());
                                        //intent.putExtra("precioOferta", productoList.get(recyclerView.getChildAdapterPosition(view)).getPrecioOferta());
                                        intent.putExtra("imagen", productoList.get(recyclerView.getChildAdapterPosition(view)).getImagen());
                                        view.getContext().startActivity(intent);
                                    }
                                });
                            }
                        });
                    }

                }
            }
        });
    }

    // TODO: Metodo que crea y devuelve la jerarquía de vistas asociada con el fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // inflar el diseño del fragmento catalogo
        View vista = inflater.inflate(R.layout.fragment_catalogo, container, false);

        // lleenamos el recyclerView de productos
        recyclerView = vista.findViewById(R.id.recyclerviewCatalogo);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // recyclerView modmo lista
        recyclerView.setLayoutManager(new GridLayoutManager(vista.getContext(), 2)); // recyclerView modo grilla

        llenarListaProductos();

        /*catalogoAdapter = new CatalogoAdapter(productoList);
        recyclerView.setAdapter(catalogoAdapter);*/

        return vista;
    }

    // TODO: Inicializa el contenido del menú superior de la actividad
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.itemProductos);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Buscar");

        super.onCreateOptionsMenu(menu, inflater);

        super.onCreateOptionsMenu(menu, inflater);
    }

    // TODO: Se activa al presionar un botón de envio
    @Override
    public boolean onQueryTextSubmit(String query) {

        // obtenemos una nueva lista de productos filtrada
        final List<Producto> filtroProductoList = filtro(productoList, query);

        // actualizamos el recyclerView con la nueva lista de productos
        catalogoAdapter = new CatalogoAdapter(filtroProductoList, getActivity().getApplicationContext());
        recyclerView.setAdapter(catalogoAdapter);

        // registrar la busqueda en una tabla SQL LITE para el historial de busquedas
        HistorialDAO historialDAO = new HistorialDAO(getActivity().getBaseContext());

        try {
            historialDAO.insertar(query);
            Toast toast= Toast.makeText(getActivity().getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        } catch (DAOException e) {
            Log.i("Catalogo", "====> " + e.getMessage());
        }

        // si hizo clic en un elemento del recyclerView, recuperamos los datos
        catalogoAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // mostrar un mensaje con el nombre del producto seleccionado
                Toast.makeText(getActivity(), "Mensaje: " + filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion(), Toast.LENGTH_SHORT).show();

                // invocamos al detalle del producto seleccionado y le pasamos algunos datos a la actividad
                Intent intent = new Intent(view.getContext(), DetalleCatalogoActivity.class);
                intent.putExtra("codigo", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getCodigo());
                intent.putExtra("descripcion", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getDescripcion());
                intent.putExtra("detalle", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getDetalle());
                intent.putExtra("precio", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getPrecio());
                //intent.putExtra("precioOferta", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getPrecioOferta());
                intent.putExtra("imagen", filtroProductoList.get(recyclerView.getChildAdapterPosition(view)).getImagen());
                view.getContext().startActivity(intent);
            }
        });

        return true;
    }

    // TODO: Se activa ante un evento de pulsación de teclado
    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            llenarListaProductos();
            return true;
        }
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return true;
    }

    // TODO: Método que filtrar
    private List<Producto> filtro(List<Producto> productoList, String query) {
        query = query.toLowerCase();
        final List<Producto> filtroProductoList = new ArrayList<>();
        for (Producto producto : productoList) {
            final String text = producto.getDescripcion().toLowerCase();
            if (text.startsWith(query)) {
                filtroProductoList.add(producto);
            }
        }
        return filtroProductoList;
    }

    // TODO: Se llama cuando se selecciona una opción del menú superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == android.R.id.home){
            // mostramos un mensaje en pantalla
            Toast.makeText(getContext(),"Has pulsado la flecha atrás", Toast.LENGTH_LONG).show();
            // le decimos a la actividad principal que queremos cargar el fragmento de catalogo
            MainActivity.opcion = 1;
            // invocamos a la actividad principal
            intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

