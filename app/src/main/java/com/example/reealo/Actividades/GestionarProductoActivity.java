package com.example.reealo.Actividades;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GestionarProductoActivity extends AppCompatActivity {

    ImageView ivImage;
    Integer REQUEST_CAMERA=1,SELECT_FILE=0;

    EditText txtNombre, txtDescripcion, txtPrecio, txtStock,txtImagen;
    Button btnRegistrar;
    Button btnEliminar;

    // TODO: Crea e inicia el activity (GestionarProductoActivity)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_producto);

        // activamos el botón de retroceso en el menú superior
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // obtenemos de la vista los componentes para asociarlos
        TextView nombre = (TextView) findViewById(R.id.txtNombreProducto);
        TextView descripcion = (TextView) findViewById(R.id.txtDescripcionProducto);
        TextView stock = (TextView) findViewById(R.id.txtStockProducto);
        TextView precio = (TextView) findViewById(R.id.txtPrecioProducto);


        Button button = (Button)findViewById(R.id.btnGrabar);
        Button buttonEli = (Button)findViewById(R.id.btnEliminar);

        // recuperamos los valores que cargamos a la actividad al seleccionar un producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.setTitle(R.string.titulo_actualizar_producto);
            button.setText(R.string.button_actualizar);
            buttonEli.setText(R.string.button_eliminar);
            buttonEli.setVisibility(View.VISIBLE);
            nombre.setText(bundle.getString("descripcion"));
            descripcion.setText(bundle.getString("detalle"));
            stock.setText(bundle.getInt("stock")+"");
            precio.setText(bundle.getDouble("precio")+"");
        } else{
            this.setTitle(R.string.titulo_registrar_producto);
            button.setText(R.string.button_registrar);
            buttonEli.setVisibility(View.GONE);
        }



        ivImage = (ImageView) findViewById(R.id.ivImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });


        // enlazamos los controles de la vista
        txtNombre = findViewById(R.id.txtNombreProducto);
        txtDescripcion = findViewById(R.id.txtDescripcionProducto);
        txtPrecio = findViewById(R.id.txtPrecioProducto);
        txtStock = findViewById(R.id.txtStockProducto);
        //txtImagen = findViewById(R.id.i);
        btnRegistrar = findViewById(R.id.btnGrabar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // creamos un objeto OkHttpClienta par realizar llamadas eficientes de red (ejor que usar HttpURLConnection y Apache HTTP Client)
                OkHttpClient client = new OkHttpClient();

                // definimos el cuerpo del post que le vamos a enviar como parametro al servicio (una cadena que contenga un JSON)
                String postBody = "{\n" +
                        " \"descripcion\": \"" + txtNombre.getText() + "\",\n" +
                        " \"detalle\": \"" + txtDescripcion.getText() + "\",\n" +
                        " \"stock\": " + txtStock.getText() + ",\n" +
                        " \"precio\": " + txtPrecio.getText() + ",\n" +
                        " \"imagen\": \"" + "vasomama.jpg"  + "\"\n" +
                        "}";
                Log.i("requestBody ====> ", postBody);


                // definimos el tipo de datos que vamos a pasar (json)
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");

                // ccreamos el cuerpo del resquest
                RequestBody requestBody = RequestBody.create(JSON, postBody);

                // para usar OkHttp necesitamos crear un objeto Request
                Request request = new Request.Builder()
                        .url("http://josel.jl.serv.net.mx/ROOT-160/webresources/testWS/registrarProducto")
                        .post(requestBody)
                        .build();
                Log.i("requestBody ====> 2 ", request.body().toString());
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
                txtDescripcion.setText("");
                txtStock.setText("");
                txtPrecio.setText("");
            }
        });


    }

    // TODO: Se llama cuando se selecciona una opción del menú superior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == android.R.id.home){
            // mostramos un mensaje en pantalla
            Toast.makeText(this,"Has pulsado la flecha atrás", Toast.LENGTH_LONG).show();
            // le decimos a la actividad principal que queremos cargar el fragmento de productos
            MainActivity.opcion = 4;
            // invocamos a la actividad principal
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void SelectImage(){

        final CharSequence[] items={"Cámara","Galería", "Cancelar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(GestionarProductoActivity.this);
        builder.setTitle("Agregar Imagen");

        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Cámara")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

                } else if (items[i].equals("Galería")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    //startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancelar")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);

        if(resultCode== Activity.RESULT_OK){

            if(requestCode==REQUEST_CAMERA){

                Bundle bundle = data.getExtras();
                final Bitmap bmp = (Bitmap) bundle.get("data");
                ivImage.setImageBitmap(bmp);

            }else if(requestCode==SELECT_FILE){

                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


}