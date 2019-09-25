package com.example.reealo.fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.reealo.R;

public class Contacto extends Fragment {

    public Contacto() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_contacto,
                container, false);
        Button mDialButton = (Button) view.findViewById(R.id.btn_numero);
        mDialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = "964240367";
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    //Toast.makeText(Layout.fragme, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // le indicamos que este fragmento tiene su propio menù de opciones
        //setHasOptionsMenu(true);

        // inflamos el layout para este fragmento
        //return inflater.inflate(R.layout.fragment_contacto  , container, false);
        return view;
    }

    // TODO: Prepara el menú de opciones antes de que se muestre el menú (usado para deshabilitar o habilitar elementos)
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // quiero ocultar la opciòn del carrito de compras
        MenuItem itemCarrito = menu.findItem(R.id.itemCarrito);
        MenuItem itemProductos = menu.findItem(R.id.itemProductos);
        itemCarrito.setVisible(false);
        itemProductos.setVisible(false);
    }

}
