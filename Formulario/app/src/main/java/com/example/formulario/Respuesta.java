package com.example.formulario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Respuesta extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resp_layout);

        String nombre = getIntent().getStringExtra("nombre");
        String  nacimiento = getIntent().getStringExtra("nacimiento");
        String genero = getIntent().getStringExtra("genero");
        String mayorDeEdad = getIntent().getStringExtra("mayorDeEdad");

        TextView nb = (TextView)findViewById(R.id.nombre);
        nb.setText(nombre);

        TextView nc = (TextView)findViewById(R.id.nacimiento);
        nc.setText(nacimiento);

        TextView gn = (TextView)findViewById(R.id.genero);
        gn.setText(genero);

        TextView me = (TextView)findViewById(R.id.mayor);
        me.setText(mayorDeEdad);


    }

    public void editarDatos(View view){
        //finish();
        Intent intent = new Intent(view.getContext(), Formulario.class);
        startActivity(intent);
    }
}
