package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Agregar extends AppCompatActivity {

    SharedPreferences preferences; SharedPreferences.Editor editor;
    EditText cuerpo;
    String cuerpoToString;

    String listaToString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
        cuerpo = (EditText)findViewById(R.id.cuerpo);

        listaToString = preferences.getString("listaToString","");
    }

    public void guardar(View view){
        //if cuerpoToString null pues nada

        cuerpoToString = cuerpo.getText().toString();

        listaToString = listaToString + cuerpoToString + ","; //añadimos lo que haya puesto el usuario y una coma

        editor.putString("listaToString",listaToString); //aplastamos la lista que había en configuración
        editor.commit();

        Toast.makeText(this, "Tarea añadida correctamente!", Toast.LENGTH_SHORT).show();

        cuerpo.setText("");
        
    }

    public void atras(View view){

        finish();
    }

}
