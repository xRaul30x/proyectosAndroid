package com.example.todolistv2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addedit_layout);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
        cuerpo = (EditText)findViewById(R.id.cuerpo);

        cuerpoToString = null;
        listaToString = preferences.getString("listaToString","");
    }

    public void guardar(View view){

        cuerpoToString = cuerpo.getText().toString();

        if(cuerpoToString.length() != 0){

            listaToString = listaToString + cuerpoToString + ","; //añadimos lo que haya puesto el usuario y una coma

            editor.putString("listaToString",listaToString); //aplastamos la lista que había en configuración
            editor.commit();

            cuerpo.setText("");
            Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this, "Debes escribir una nota antes!", Toast.LENGTH_SHORT).show();
        }

    }

    public void atras(View view){

        finish();
    }

}
