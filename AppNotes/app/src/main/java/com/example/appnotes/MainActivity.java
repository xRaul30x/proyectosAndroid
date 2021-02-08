package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;
    static boolean[][] pulsada;
    static boolean[][] tieneNota;
    static ArrayList<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);

        //rellenamos todas las notas guardadas en la base de datos

        pulsada = new boolean[6][4];
        tieneNota = new boolean[6][4];

        for (int f = 0; f < 6; f++) { //filas
            for (int c = 0; c < 4; c++) { //columnas

                pulsada[f][c] = false;
                tieneNota[f][c] = false;
            }
        }

        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);
    }

    public void nuevaNota(View view){

        //Intent intent = new Intent(this, AgregarEditar.class);
        //startActivity(intent);

        dibujoNota drawNota = new dibujoNota(this);
        layout.addView(drawNota);
    }

    public void salir(View view){

    }
}