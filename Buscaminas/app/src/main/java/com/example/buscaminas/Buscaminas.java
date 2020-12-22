package com.example.buscaminas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Buscaminas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscaminas_layout);

        RelativeLayout layout = findViewById(R.id.layout);

        Tablero pintarTablero = new Tablero(this);

        layout.addView(pintarTablero);





    }

    public void volver(View view){
        Intent intent = new Intent(view.getContext(),MenuPrincipal.class);
        startActivity(intent);
    }
}
