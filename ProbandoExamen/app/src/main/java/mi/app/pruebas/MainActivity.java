package mi.app.pruebas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creacion alertDialog
        Button ADButton = (Button) findViewById(R.id.AD);
        ADButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearDialogo(v);
            }
        });

        //En consola o pantalla de modo alerta
        Button ConsoleButton = (Button) findViewById(R.id.Consola);
        ConsoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esto lo muestra en la consola
                Log.i("mensaje", "Hola mundo soy un log.i");

                //Crea una alerta en la pantalla
                Toast.makeText(getApplicationContext(), "Hola mundo soy un toast", Toast.LENGTH_SHORT).show();
            }
        });

        //Coger valores de botones y textos.
        Button valores = (Button) findViewById(R.id.VT);
        valores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hay que declarar la clase en el manifest
                Intent comunicacion = new Intent(v.getContext(), valores.class);
                startActivity(comunicacion);
            }
        });
        //Recoger valores de un intent
        String nombre = getIntent().getStringExtra("nb");
        TextView mostrarBienvenida = (TextView) findViewById(R.id.IntentValue);
        mostrarBienvenida.setText("Bienvenido "+ nombre);

        //Todos los tipos de intents
        final Button intents = (Button) findViewById(R.id.intents);
        intents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),intents.class);
                startActivity(comunicacion);
            }
        });

        //Verificacion Radio Buttons
        Button radioButton = (Button) findViewById(R.id.RadioButtons);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),RadioButton.class);
                startActivity(comunicacion);

            }
        });

        //Verificacion de Check Box
        Button checkbox = (Button) findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent (v.getContext(),checkBox.class);
                startActivity(comunicacion);
            }
        });

        //Spinners
        Button spinner = (Button) findViewById(R.id.spinner);
        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comunicacion = new Intent(v.getContext(),spinner.class);
                startActivity(comunicacion);
            }
        });

    }
    public void crearDialogo(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Prueba");
        alert.setTitle("gfgf");
        alert.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

}