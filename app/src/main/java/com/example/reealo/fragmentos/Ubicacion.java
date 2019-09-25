package com.example.reealo.fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.reealo.Clases.Marcador;
import com.example.reealo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class Ubicacion extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap googleMap;
    List<Marcador> marcadorList = new ArrayList<>();

    public Ubicacion() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // inflamos el layout para este fragmento
        View vista = inflater.inflate(R.layout.fragment_ubicacion, container, false);

        mapView = (MapView) vista.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return vista;
    }

    // TODO: Metodo que obtiene las ubicaciones del servicio
    private void obtenerMarcadores() {
        // creamos un objeto OkHttpClient par realizar llamadas eficientes de red
        OkHttpClient client = new OkHttpClient();

        // para usar OkHttp necesitamos crear un objeto Request
        Request request = new Request.Builder()
                .url("http://joselyn.jl.serv.net.mx/ROOT-160/webresources/testWS/listarUbicaciones")
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
                    Type listType = new TypeToken<ArrayList<Marcador>>() {
                    }.getType();

                    // convertimos un array Json a ArrayList
                    marcadorList = gson.fromJson(cadenaJson, listType);
                }
            }
        });
    }

    // TODO: Se activa cuando el mapa esta listo para ser utilizado
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);
        /*googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);*/
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.2380498, -76.7838627)).title("Lurin, Fundo Zeballo").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.3416667, -76.8252778)).title("Punta Hermosa"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.2510966, -76.9065923)).title("Pachacamac"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.2380498, -76.7838627), 15));
    }

    // TODO: Prepara el menú de opciones antes de que se muestre el menú (usado para deshabilitar o habilitar elementos)
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // quiero ocultar la opciòn del carrito de compras
        MenuItem itemBuscar = menu.findItem(R.id.itemProductos);
        MenuItem itemCarrito = menu.findItem(R.id.itemCarrito);
        itemBuscar.setVisible(false);
        itemCarrito.setVisible(false);
    }

}
