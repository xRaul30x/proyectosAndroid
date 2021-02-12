package com.example.examenraulragel;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//Raul Ragel Sousa 50572932A
public class Comenzar extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comenzar);

        final String[] refranes = new String[]{"A río revuelto, ganancia de pescadores",
                "El que mucho abarca, poco aprieta",
                "Burro grande, ande o no ande",
                "Con la barriga vacía, ninguno muestra alegría",
                "El tiempo todo lo cura menos vejez y locura",
                "Haz bien y no mires a quien",
                "La suerte de la fea, la guapa la desea",
                "Más vale prevenir que curar",
                "Piensa mal y acertarás",
                "A nadie le amarga un dulce" };

        final int[] colores = new int[]{         Color.rgb(64,89,120),         Color.rgb(149,165,124),         Color.rgb(217,184,162),         Color.rgb(64,89,120),         Color.rgb(191,134,134),         Color.rgb(3, 169, 244),         Color.rgb(233, 30, 99),         Color.rgb(103, 58, 183),         Color.rgb(63, 81, 181),         Color.rgb(33, 150, 243),         Color.rgb(0, 150, 136),         Color.rgb(255, 193, 7) };

        final String[] tamanos = {"Seleccione un tamaño","Pequeño","Normal","Grande"};

        Spinner spinerTams = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(Comenzar.this,android.R.layout.simple_spinner_item,tamanos);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinerTams.setAdapter(aa);
        spinerTams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final TextView textoRefran = (TextView)findViewById(R.id.textRefran);

                switch (tamanos[position]) {

                    case "Pequeño":
                        textoRefran.setTextSize(12);
                        break;
                    case "Normal":
                        textoRefran.setTextSize(25);
                        break;
                    case "Grande":
                        textoRefran.setTextSize(50);
                        break;
                    case "Seleccione un tamaño":
                        Toast.makeText(view.getContext(), "Debes seleccionar un tamaño", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        final Button cambiar = (Button) findViewById(R.id.cambiar);
        final Button atras = (Button) findViewById(R.id.atras2);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView textoRefran = (TextView)findViewById(R.id.textRefran);

                RadioGroup lista = (RadioGroup)findViewById(R.id.lista);
                int idSeleccionado = lista.getCheckedRadioButtonId(); //nos devuelde la id del seleccionado
                RadioButton seleccionado = (RadioButton)findViewById(idSeleccionado); //esta id puede ser buscada por el findViewById

                String seleccionadoText = seleccionado.getText().toString();

                if(seleccionadoText.equals("Refran")){ //cambiamos refran

                    int numAleat = (int)(Math.random()*refranes.length-1);

                    textoRefran.setText(refranes[numAleat]);

                }else if(seleccionadoText.equals("Color")){ //cambiamos color

                    int numAleat = (int)(Math.random()*colores.length-1);

                    textoRefran.setTextColor(colores[numAleat]);
                }else{
                    Toast.makeText(v.getContext(), "Debes seleccionar un tamaño", Toast.LENGTH_SHORT).show();
                }


            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void llamar(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "629043960"));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void maps(View view) {

        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: 40.453151, -3.688405"));

        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.es/"));

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }
}
