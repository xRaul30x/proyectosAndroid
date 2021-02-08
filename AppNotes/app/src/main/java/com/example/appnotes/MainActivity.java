package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    static boolean[][] pulsada;
    static boolean[][] tieneNota;
    Set<String> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);
        pulsada = new boolean[6][4];
        tieneNota = new boolean[6][4];
        listaNotas = new HashSet<String>();

        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);

        actualizar();

    }

    public void actualizarView(View view){ //actualizar desde el botón

        actualizar();
    }

    public void actualizar(){ //en este método reinicializamos las casillas y cogemos los datos del sharedPreferences

        for (int f = 0; f < 6; f++) { //filas
            for (int c = 0; c < 4; c++) { //columnas

                pulsada[f][c] = false;
                tieneNota[f][c] = false;
            }
        }

        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE); //cogemos la configuracion
        listaNotas = preferences.getStringSet("lista", new HashSet<String>());

        //por cada nota guardada, pintamos una como referencia a la misma
        for (int i=0;i<listaNotas.size();i++){

            pintarSiguiente();
        }
    }


    public void nuevaNota(View view){

        //en este intent deberíamos llevarnos los datos de la nota a modificar
        Intent intent = new Intent(this, AgregarEditar.class);
        startActivity(intent);

        //dibujoNota drawNota = new dibujoNota(this);
        //layout.addView(drawNota);
    }

    public void pintarSiguiente(){
        for (int f = 0; f < 6; f++) { //filas
            for (int c = 0; c < 4; c++) { //columnas

                if(!tieneNota[f][c]){ //vamos buscando hasta encontrar la siguiente que no tenga nota

                    tieneNota[f][c] = true; //cuando la encontramos, colocamos una nota

                    dibujoNota drawNota = new dibujoNota(this,f,c);
                    layout.addView(drawNota);
                    return;
                }

            }
        }
    }

    public void borrar(View view){
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Toast.makeText(this, "Notas borradas de la base de datos", Toast.LENGTH_SHORT).show();
    }

    public void salir(View view){
        //this.onCreate(null);
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE); //cogemos la configuracion
        listaNotas = preferences.getStringSet("lista", new HashSet<String>());
        Toast.makeText(this, ""+listaNotas.toString()+" "+listaNotas.size(), Toast.LENGTH_SHORT).show();

    }
}