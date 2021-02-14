package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Agregar extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EditText cuerpo;
    String cuerpoToString;
    String listaToString;

    String contenidoEdit;
    boolean modoEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
        cuerpo = (EditText)findViewById(R.id.cuerpo);

        //cuerpoToString = null;
        listaToString = preferences.getString("listaToString","");

        contenidoEdit = getIntent().getStringExtra("contenidoEdit");

        if(contenidoEdit != null){

            modoEditor = true;
            cuerpo.setText(contenidoEdit);
            //Toast.makeText(this, "Modo editor", Toast.LENGTH_SHORT).show();
        }else{

            modoEditor = false;
        }

    }

    public void guardar(View view){

        cuerpoToString = cuerpo.getText().toString().trim();

        if(cuerpoToString.length() != 0){ //si hay algo escrito...

            if(modoEditor){ //si estamos en modo edición, reemplazamos

                listaToString = preferences.getString("listaToString",null); //nunca va  ser null

                String replaceInLista = listaToString.replace(contenidoEdit,cuerpoToString); //reemplazamos el target por lo que ha puesto el usuario

                editor.putString("listaToString",replaceInLista); //aplastamos la lista que había en configuración
                editor.commit();
                
                Toast.makeText(this, "Editado correctamente", Toast.LENGTH_SHORT).show();

            }else{ //si no estamos en modo edición, añadimos

                listaToString = listaToString + cuerpoToString + ","; //añadimos lo que haya puesto el usuario y una coma

                editor.putString("listaToString",listaToString); //aplastamos la lista que había en configuración
                editor.commit();

                cuerpo.setText("");
                Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
            }

        }else{ //si no hay nada escrito...

            cuerpo.setText(""); //si el usuario sólo ha puesto espacios en blanco, se los quitamos
            Toast.makeText(this, "Debes escribir una nota antes!", Toast.LENGTH_SHORT).show();
        }

    }

    public void atras(View view){

        finish();
    }

}
