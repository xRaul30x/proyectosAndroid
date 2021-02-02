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

        /*
        String nombre = getIntent().getStringExtra("nombre");
        String  nacimiento = getIntent().getStringExtra("nacimiento");
        String genero = getIntent().getStringExtra("genero");
        String mayorDeEdad = getIntent().getStringExtra("mayorDeEdad");
        */

        Bundle datos = getIntent().getExtras();
        String nombre = datos.getString("nombre");
        String  nacimiento = datos.getString("nacimiento");
        String genero = datos.getString("genero");
        String mayorDeEdad = datos.getString("mayorDeEdad");

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
        Intent intent = new Intent(this, Formulario.class);
        setResult(RESULT_CANCELED, intent);
        finish();

    }

    public void guardarDatos(View view){
        Intent intent = new Intent(this, Formulario.class);
        setResult(RESULT_OK, intent);
        finish();

    }


}
