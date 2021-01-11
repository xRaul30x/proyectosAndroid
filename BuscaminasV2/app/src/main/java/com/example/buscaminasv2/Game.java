package com.example.buscaminasv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        RelativeLayout layout = findViewById(R.id.layout);
        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);
    }

    public void volver(View view){
        Intent intent = new Intent(view.getContext(),MenuPrincipal.class);
        startActivity(intent);
    }
}