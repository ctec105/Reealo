package com.example.reealo.fragmentos;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.reealo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubicacion extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    GoogleMap googleMap;

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

    // TODO: Se activa cuando el mapa esta listo para ser utilizado
    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setTrafficEnabled(true);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.04592, -77.030565)).title("Centro de Lima").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.044956, -77.029831)).title("Palacio de Gobierno"));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(-12.046661, -77.029544)).title("Catedral"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-12.04592, -77.030565), 15));
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
