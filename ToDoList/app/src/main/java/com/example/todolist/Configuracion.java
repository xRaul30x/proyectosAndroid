package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion_layout);


    }

    public void guardar(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void atras(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
