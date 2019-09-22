package com.example.reealo.fragmentos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reealo.Actividades.LogueoActivity;
import com.example.reealo.Adaptadores.CarritoAdapter;
import com.example.reealo.Clases.Producto;
import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Carrito extends Fragment {

    //
    private List<Producto> productoList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CarritoAdapter carritoAdapter;
    private TextView lblSubTotal, lblDescuento, lblEnvio, lblTotal;
    private Button btnFinalizarCompra;


    private double precioTotal, precioOfertaTotal, subTotal = 0, envio = 10.00, impDescuento = 0, total = 0;


    private List<Producto> cesta = new ArrayList<Producto>();
    private SharedPreferences carrito;
    private Gson gson = new Gson();


    public Carrito() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // le indicamos que este fragmento tiene su propio menù de opciones
        setHasOptionsMenu(true);

        // recuperamos datos de nuestra preferencia
        carrito = this.getActivity().getSharedPreferences("carrito", Context.MODE_PRIVATE);
        String guardado = carrito.getString("cesta", "");
        Type type = new TypeToken<ArrayList<Producto>>() {
        }.getType();
        cesta = gson.fromJson(guardado, type);

        View vista = null;
        if (cesta.size() > 0) {
            // inflamos el layout para para el fragmento carrito
            vista = inflater.inflate(R.layout.fragment_carrito, container, false);

            // enlazamos los controles para asociarlos
            /*recyclerView = vista.findViewById(R.id.recyclerviewCarrito);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            lblSubTotal = vista.findViewById(R.id.lblSubTotalCarrito);
            lblDescuento = vista.findViewById(R.id.lblDescuentoCarrito);
            lblEnvio = vista.findViewById(R.id.lblEnvioCarrito);
            lblTotal = vista.findViewById(R.id.lblTotalCarrito);*/

            // Modo 1: creamos el cuerpo de la cesta por codigo
            cuerpoCesta(vista);

//            // Modo 2: Le pasamos la cesta al RecyclerView
//            //resumenCompra();
//            carritoAdapter = new CarritoAdapter(cesta, this.getContext());
//            recyclerView.setAdapter(carritoAdapter);

            // implementamos le botón finalizar compra
            btnFinalizarCompra = vista.findViewById(R.id.btnFinalizarCompra);
            btnFinalizarCompra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), LogueoActivity.class);
                    intent.putExtra("total", total);
                    startActivity(intent);
                }
            });
        } else {
            // inflamos el layout para para el fragmento carrito vacio
            vista = inflater.inflate(R.layout.fragment_carrito_vacio, container, false);
            Button btnSeguirComprando = vista.findViewById(R.id.btnSeguirComprando);
            btnSeguirComprando.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Has pulsado el botón Continuar", Toast.LENGTH_SHORT).show();
                    MainActivity.opcion = 1;
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }

        return vista;
    }


    // Metodo que arma el cuerpo de la cesta
    private void cuerpoCesta(final View vista) {

        final LinearLayout ll = (LinearLayout) vista.findViewById(R.id.llCesta);
        ll.removeAllViews();

        LinearLayout linearLayout = null;

        //double total = 0;
        total = 0;

        if (cesta != null) {
            for (int i = 0; i < cesta.size(); i++) {
                Producto producto = cesta.get(i);
                linearLayout = new LinearLayout(this.getContext());
                linearLayout.setOrientation(linearLayout.HORIZONTAL);

                final TextView lblDescripcion = new TextView(this.getContext());
                lblDescripcion.setText(producto.getDescripcion());
                lblDescripcion.setLayoutParams(new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblDescripcion.setPadding(0, 0, 10, 0);

                final TextView lblPrecio = new TextView(this.getContext());
                lblPrecio.setText("S/. " + producto.getPrecio());
                lblPrecio.setLayoutParams(new LinearLayout.LayoutParams(230, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblPrecio.setPadding(0, 0, 10, 0);
                lblPrecio.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                final TextView lblCantidad = new TextView(this.getContext());
                lblCantidad.setText(producto.getCantidad() + "");
                lblCantidad.setLayoutParams(new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblCantidad.setPadding(0, 0, 10, 0);
                lblCantidad.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                final TextView lblMonto = new TextView(this.getContext());
                double montoCompra = producto.getPrecio() * producto.getCantidad();
                lblMonto.setText("S/. " + montoCompra);
                lblMonto.setLayoutParams(new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblMonto.setPadding(0, 0, 50, 0);
                lblMonto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                final Button btnRestar = new Button(this.getContext());
                btnRestar.setText("-");
                btnRestar.setLayoutParams(new LinearLayout.LayoutParams(150, ViewGroup.LayoutParams.WRAP_CONTENT));
                btnRestar.setPadding(0, 10, 0, 0);

                final String codPro = cesta.get(i).getCodigo();

                btnRestar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int i = 0; i < cesta.size(); i++) {
                            if (cesta.get(i).getCodigo().equals(codPro)) {
                                cesta.get(i).setCantidad(cesta.get(i).getCantidad() - 1);
                                if (cesta.get(i).getCantidad() < 1) {
                                    cesta.remove(i);
                                }
                            }
                        }

                        // actualizar mi preferencia
                        String jsonlist = gson.toJson(cesta);
                        carrito = getActivity().getSharedPreferences("carrito", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = carrito.edit();
                        editor.putString("cesta", jsonlist);
                        editor.commit(); // guardamos datos y cerramos la preferencia

                        // actualiza la cesta
                        cuerpoCesta(vista);

                        // actualizar el fragmento
                        Fragment frg = null;
                        Class fragmentClass;
                        fragmentClass = Carrito.class;
                        try {
                            frg = (androidx.fragment.app.Fragment) fragmentClass.newInstance();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        getFragmentManager().beginTransaction().replace(R.id.contenedor_principal, frg).commit();
                    }
                });

                linearLayout.addView(lblDescripcion);
                linearLayout.addView(lblPrecio);
                linearLayout.addView(lblCantidad);
                linearLayout.addView(lblMonto);
                linearLayout.addView(btnRestar);

                ll.addView(linearLayout);

                total += montoCompra;
            }

            final TextView lblTotal = new TextView(this.getContext());
            lblTotal.setText("Total a Pagar:");
            lblTotal.setLayoutParams(new LinearLayout.LayoutParams(630, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
            lblTotal.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            lblTotal.setPadding(0, 50, 0, 0);

            final TextView txtTotal = new TextView(this.getContext());
            txtTotal.setText("S/. " + total);
            txtTotal.setLayoutParams(new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT, 1));

            txtTotal.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            txtTotal.setPadding(0, 0, 10, 0);
            txtTotal.setPadding(0, 50, 0, 0);

            LinearLayout linearLayout2 = new LinearLayout(this.getContext());
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);

            linearLayout2.addView(lblTotal);
            linearLayout2.addView(txtTotal);
            ll.addView(linearLayout2);

            //resumenCompra();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Tu carrito esta vacio", Toast.LENGTH_SHORT).show();
        }

    }


    // Metodo que arma el cuerpo de la cesta
//    public void resumenCompra() {
//
//        total = 0;
//        for (int i = 0; i < cesta.size(); i++) {
//            Producto p = cesta.get(i);
//
////            precioTotal = p.getPrecio() * p.getCantidad();
////            precioOfertaTotal = p.getPrecioOferta() * p.getCantidad();
////            subTotal += precioOfertaTotal;
////            impDescuento = subTotal * 0.20;
////            total = subTotal - impDescuento + envio;
//
//            precioTotal = p.getPrecio() * p.getCantidad();
//            subTotal += precioTotal;
//            impDescuento = subTotal * 0.20;
//            total = subTotal - impDescuento + envio;
//
//            lblSubTotal.setText("S/. " + subTotal);
//            lblDescuento.setText("S/. " + impDescuento);
//            lblEnvio.setText("S/. " + envio);
//            lblTotal.setText("S/. " + total);
//
//            //final String codPro = cesta.get(i).getCodigo();
//
//            //productoList.add(new Producto(p.getCodigo(), p.getDescripcion(), p.getCantidad(), precioTotal, precioOfertaTotal, subTotal, impDescuento, envio, total, p.getImagen()));
//        }
//
//    }

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
