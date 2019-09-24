package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reealo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistrarClienteActivity extends AppCompatActivity {

    EditText txtApellido, txtNombre, txtCorreo, txtContraseña;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente);

        // cambiamos el tìtulo del menu de la actividad
        this.setTitle(R.string.titulo_registrar_cliente);

        // Mosmostramos el botón de retroceso
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // enlazamos los controles de la vista
        txtApellido = findViewById(R.id.txtApellidoUsuario);
        txtNombre = findViewById(R.id.txtNombreUsuario);
        txtCorreo = findViewById(R.id.txtCorreoUsuario);
        txtContraseña = findViewById(R.id.txtContraseñaUsuario);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuario);

        // si hizo clic en el botòn registrarse
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // creamos un objeto OkHttpClienta par realizar llamadas eficientes de red (ejor que usar HttpURLConnection y Apache HTTP Client)
                OkHttpClient client = new OkHttpClient();

                // definimos el cuerpo del post que le vamos a enviar como parametro al servicio (una cadena que contenga un JSON)
                //String postBody = "{\"nombre\":\"José\",\"apellido\":\"Toro\",\"correo\":\"jose@gmail.com\",\"contraseña\":\"123456\"}";
                String postBody = "{\n" +
                        " \"nombre\": \"" + txtNombre.getText() + "\",\n" +
                        " \"apellido\": \"" + txtApellido.getText() + "\",\n" +
                        " \"correo\": \"" + txtCorreo.getText() + "\",\n" +
                        " \"contraseña\": \"" + txtContraseña.getText() + "\"\n" +
                        "}";
                Log.i("requestBody ====> ", postBody);

                // definimos el tipo de datos que vamos a pasar (json)
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                // ccreamos el cuerpo del resquest
                RequestBody requestBody = RequestBody.create(JSON, postBody);

                // para usar OkHttp necesitamos crear un objeto Request
                Request request = new Request.Builder()
                        .url("http://joselyn.jl.serv.net.mx/ROOT-160/webresources/testWS/registrarUsuario")
                        .post(requestBody)
                        .build();

                // para realizar llamadas asincrónicas, mediante el objeto Call usamos el método enqueue
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Codigo inesperado ====> " + response);
                        } else {
                            final String cadenaJson = response.body().string();
                            Log.i("cadenaJson ====> ", cadenaJson);
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    if (cadenaJson.equals("1")) {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Se insertó correctamente", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    } else {
                                        Toast toast = Toast.makeText(getApplicationContext(), "No se insertó correctamente", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast.show();
                                    }
                                }
                            });
                        }
                    }
                });

                txtNombre.setText("");
                txtApellido.setText("");
                txtCorreo.setText("");
                txtContraseña.setText("");
            }
        });

    }

}