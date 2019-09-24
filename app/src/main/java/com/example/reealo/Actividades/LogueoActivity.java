package com.example.reealo.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.Clases.Producto;
import com.example.reealo.Clases.Usuario;
import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogueoActivity extends AppCompatActivity {

    // variables globales para activity
    Button btnCrearCuenta, btnIniciarSesion;
    TextView txtCorreo, txtContraseña;
    ArrayList<Usuario> usuariosList;
    double total = 0;

    // variables globales para la cesta
    ArrayList<Producto> cesta = new ArrayList<Producto>();
    SharedPreferences carrito;
    Gson gson = new Gson();

    // variables globales para el procesador de pago paypal
    PayPalConfiguration m_configuration;
    String m_paypalClientld = "AbcpVj-rbtCXFJ5iw27-2Ujoz51K_y5m21TsGKT4RsmWmvLJq9xJTlHfiy-EoLP2Q-wXmsByzadqK_5V"; // clientid paypal
    Intent m_service;
    int m_paypalRequestCode = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        // recuperamos el total
        Bundle recupera = getIntent().getExtras();
        if (recupera != null) {
            total = recupera.getDouble("total");
        }
        Toast.makeText(getApplicationContext(), "Total: " + total, Toast.LENGTH_SHORT).show();

        // obtenemos los controles para asociarlos
        txtCorreo = findViewById(R.id.txtCorreoLogin);
        txtContraseña = findViewById(R.id.txtContraseñaLogin);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnCrearCuenta = findViewById(R.id.btnCrearCuenta);

        // si hizo clic en el botón iniciar sesión
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // invocamos al servicio
                validarUsuario();
            }
        });

        // si hizo clic en el botón crear cuenta
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  redirecionamos a la actividad crear nueva cuenta
                Intent i = new Intent(getApplicationContext(), RegistrarClienteActivity.class);
                startActivity(i);
            }
        });

        // paypal
        m_configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(m_paypalClientld);
        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
        startService(m_service);
    }

    public void validarUsuario() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://joselyn.jl.serv.net.mx/ROOT-160/webresources/testWS/validarUsuario?correo="
                        + txtCorreo.getText() + "&contraseña=" + txtContraseña.getText())
                .build();

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
                    String cadenaJson = response.body().string();
                    Log.i("cadenaJson ====>", cadenaJson);

                    Gson gson = new Gson();

                    Type listType = new TypeToken<ArrayList<Usuario>>() {
                    }.getType();
                    usuariosList = gson.fromJson(cadenaJson, listType);

                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (usuariosList.size() > 0) {
                                // invocamos a la actividad de pago de Paypal
                                PayPalPayment payment = new PayPalPayment(new BigDecimal(total), "USD", "Pagar con Paypal", PayPalPayment.PAYMENT_INTENT_SALE);
                                Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
                                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_configuration);
                                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
                                startActivityForResult(intent, m_paypalRequestCode);

                                /*// invocamos a la actividad prinicpal
                                Toast.makeText(getApplicationContext(), "Usuario correcto", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);*/
                            } else {
                                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }

    /*public void iniciarSesion(View v){
        //  redirecionamos a la actividad prinicipal
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == m_paypalRequestCode) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    String state = confirmation.getProofOfPayment().getState();
                    // si el pago ha sido aprovado
                    if (state.equals("approved")) {
                        Toast.makeText(getApplicationContext(), "Pago aprobado", Toast.LENGTH_SHORT).show();

                        // limpiamos la cesta
                        cesta.clear();

                        // grabamos la preferencia
                        String jsonlist = gson.toJson(cesta);
                        carrito = getSharedPreferences("carrito", MODE_PRIVATE);
                        SharedPreferences.Editor editor = carrito.edit();
                        editor.putString("cesta", jsonlist);

                        //comint es guardar datos y  cerrar preferncias
                        editor.commit();

                        //  redirecionamos a la actividad prinicipal
                        Intent i = new Intent(this, MainActivity.class);
                        startActivity(i);
                    } else
                        Toast.makeText(getApplicationContext(), "Error en el pago", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Confirmacion Vacia", Toast.LENGTH_SHORT).show();
            }
        }
    }

}