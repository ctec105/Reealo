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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reealo.MainActivity;
import com.example.reealo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GestionarProductoActivity extends AppCompatActivity {

    ImageView ivImage;
    Integer REQUEST_CAMERA=1,SELECT_FILE=0;
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
        Button button = (Button)findViewById(R.id.btnGrabar);

        // recuperamos los valores que cargamos a la actividad al seleccionar un producto
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            this.setTitle(R.string.titulo_actualizar_producto);
            button.setText(R.string.button_actualizar);
            nombre.setText(bundle.getString("nombre"));
            descripcion.setText(bundle.getString("descripcion"));
        } else{
            this.setTitle(R.string.titulo_registrar_producto);
            button.setText(R.string.button_registrar);
        }



        ivImage = (ImageView) findViewById(R.id.ivImage);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
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