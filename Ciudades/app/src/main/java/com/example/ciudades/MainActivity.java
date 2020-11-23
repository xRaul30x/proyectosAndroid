package com.example.ciudades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity{


    String[] paises = { "España", "Alemania", "Bélgica"};
    String[] espana = { "Guadalupe", "Lastres", "Madrid"};
    String[] alemania = { "Berlin", "Füssen", "Köhl", "Bamberg"};
    String[] belgica = { "Brujas", "Gante", "Ambere"};
    String[] ciudades;

    Spinner spinerPaises;
    Spinner spinerCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinerPaises = (Spinner) findViewById(R.id.paises);
        ArrayAdapter aa = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,paises);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerPaises.setAdapter(aa);

        spinerPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (paises[position]){
                    case "España":
                        ArrayAdapter aEsp = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,espana);
                        aEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinerCiudades.setAdapter(aEsp);

                        ciudades = espana;
                        break;
                    case "Alemania":
                        ArrayAdapter aAle = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,alemania);
                        aAle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinerCiudades.setAdapter(aAle);

                        ciudades = alemania;
                        break;
                    case "Bélgica":
                        ArrayAdapter aBel = new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,belgica);
                        aBel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinerCiudades.setAdapter(aBel);

                        ciudades = belgica;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        spinerCiudades = (Spinner)findViewById(R.id.ciudades);
        spinerCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    TextView ciudadSeleccionada = (TextView)findViewById(R.id.seleccionado);
                    ciudadSeleccionada.setText(ciudades[position]);

                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

}