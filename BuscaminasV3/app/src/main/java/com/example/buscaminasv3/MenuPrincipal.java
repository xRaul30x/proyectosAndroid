package com.example.buscaminasv3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    //static int bombas = 6; //esta variable puede ser cambiada desde configuración y se utiliza en Tablero

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        Button jugar = (Button)findViewById(R.id.jugar);
        Button config = (Button)findViewById(R.id.config);
        Button salir = (Button)findViewById(R.id.salir);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Game.class);
                startActivity(intent);
            }
        });

        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Configuracion.class);
                startActivity(intent);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(MenuPrincipal.this);

                alert.setTitle("Saliendo de Buscaminas");

                alert.setMessage("Estás seguro que quieres salir?");

                alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //antes de salir se reinicia la configuración
                        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit(); //editor de preferencias
                        editor.putString("dificultad","normal");
                        editor.putInt("nBombas",10);
                        editor.commit();
                        finish();
                    }
                });

                alert.setNeutralButton("Cancelar", null);//Tercera opción a realizar.

                alert.show();
            }
        });
    }
}