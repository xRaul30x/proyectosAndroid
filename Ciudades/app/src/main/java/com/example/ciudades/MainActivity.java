package com.example.ciudades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{


    String[] paises = { "España", "Alemania", "Bélgica"};
    String[] espana = { "Guadalupe", "Lastres", "Madrid"};
    String[] alemania = { "Berlin,", "Füssen, Köhl", "Bamberg"};
    String[] belgica = { "Brujas", "Gante", "Ambere"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spinerPaises = (Spinner) findViewById(R.id.paises);
        spinerPaises.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,paises);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinerPaises.setAdapter(aa);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(),paises[position] , Toast.LENGTH_SHORT).show();

        Spinner spinerCiudades = (Spinner) findViewById(R.id.ciudades);
        spinerCiudades.setOnItemSelectedListener(this);

        switch (paises[position]){
            case "España":
                ArrayAdapter aEsp = new ArrayAdapter(this,android.R.layout.simple_spinner_item,espana);
                aEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerCiudades.setAdapter(aEsp);

                TextView ciuEsp = (TextView)findViewById(R.id.seleccionado);
                ciuEsp.setText(espana[position]);
                break;
            case "Alemania":
                ArrayAdapter aAle = new ArrayAdapter(this,android.R.layout.simple_spinner_item,alemania);
                aAle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerCiudades.setAdapter(aAle);

                TextView ciuAle = (TextView)findViewById(R.id.seleccionado);
                ciuAle.setText(alemania[position]);
                break;
            case "Bélgica":
                ArrayAdapter aBel = new ArrayAdapter(this,android.R.layout.simple_spinner_item,belgica);
                aBel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinerCiudades.setAdapter(aBel);

                TextView ciuBel = (TextView)findViewById(R.id.seleccionado);
                ciuBel.setText(belgica[position]);
                break;
            default:
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}