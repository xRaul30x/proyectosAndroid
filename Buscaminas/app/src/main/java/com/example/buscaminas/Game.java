package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Game extends AppCompatActivity {

    static boolean modoBanderas = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        RelativeLayout layout = findViewById(R.id.layout);
        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);


    }

    public void volver(View view){
        finish();
        Intent intent = new Intent(view.getContext(),MenuPrincipal.class);
        startActivity(intent);
    }

    public void reiniciar(View view){
        finish();
        Intent intent = new Intent(view.getContext(),Game.class);
        startActivity(intent);
    }



}