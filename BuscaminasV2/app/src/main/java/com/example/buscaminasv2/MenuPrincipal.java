package com.example.buscaminasv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipal extends AppCompatActivity {

    static int bombas = 6; //esta variable puede ser cambiada desde configuración y se utiliza en Tablero

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

                        bombas = 6; //reiniciamos la variable por si ha sido cambiada durante el juego
                        finish();
                    }
                });

                alert.setNeutralButton("Cancelar", null);//Tercera opción a realizar.

                alert.show();
            }
        });
    }
}