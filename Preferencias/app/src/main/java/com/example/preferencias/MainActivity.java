package com.example.preferencias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText campoNombre;
    EditText campoDni;
    TextView prefUsuario;
    TextView prefDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoNombre = (EditText)findViewById(R.id.nombre);
        campoDni = (EditText)findViewById(R.id.dni);
        prefUsuario = (TextView)findViewById(R.id.prefUsuario);
        prefDni = (TextView)findViewById(R.id.prefDni);

        cargarPreferencias();
    }

    public void cargarPreferencias() {
        SharedPreferences pref = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = pref.getString("user","No hay datos.");
        String identificacion = pref.getString("id","No hay datos.");

        prefUsuario.setText(usuario);
        prefDni.setText(identificacion);
    }

    public void guardarPreferencias(View view){
        SharedPreferences pref = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String usuario = campoNombre.getText().toString();
        String identificacion = campoDni.getText().toString();

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user",usuario);
        editor.putString("id",identificacion);

        editor.commit();
        cargarPreferencias();
    }

    public void aceptar(View view){
        //Intent intent = new Intent(this, ConsultarPreferencias.class);
        //startActivity(intent);
    }
}