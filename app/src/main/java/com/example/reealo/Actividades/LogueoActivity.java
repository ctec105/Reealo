package com.example.reealo.Actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.Clases.Usuario;
import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogueoActivity extends AppCompatActivity {

    private Button btnCrearCuenta, btnIniciarSesion;
    private TextView txtCorreo, txtContraseña;
    private double total = 0;
    private ArrayList<Usuario> usuariosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueo);

        // recuperamos el total
        Bundle recupera = getIntent().getExtras();
        if (recupera != null) {
            total = recupera.getDouble("total");
        }

        //test
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

    }

    public void validarUsuario() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://josel.jl.serv.net.mx/ROOT-160/webresources/testWS/validarUsuario?correo="
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
                                Toast.makeText(getApplicationContext(), "Usuario correcto", Toast.LENGTH_SHORT).show();
                                //  redirecionamos a la actividad prinicipal
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                                ;
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

}