package com.example.appnotes2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class AgregarEditar extends AppCompatActivity {

    EditText titulo, cuerpo;
    Set<String> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_layout);

        titulo = (EditText)findViewById(R.id.titulo);
        cuerpo = (EditText)findViewById(R.id.cuerpo);
        listaNotas = new HashSet<String>();
    }

    public void guardar(View view){

        String tit = titulo.getText().toString();
        String cue = cuerpo.getText().toString();

        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE); //cogemos la configuracion

        listaNotas = preferences.getStringSet("lista",new HashSet<String>()); //guardamos lo que haya en nuestro array
        Set<String> aux = new HashSet<String>(listaNotas);

        aux.add(tit+":"+cue); //añadimos la nueva nota a nuestro array

        SharedPreferences.Editor editor = preferences.edit(); //editor de preferencias
        editor.putStringSet("lista",aux); //aplastamos la lista que había en configuración
        editor.commit();

        Toast.makeText(this, ""+aux.toString(), Toast.LENGTH_SHORT).show();

        //finish();
    }

    public void cancelar(View view){
        finish();
    }

}
