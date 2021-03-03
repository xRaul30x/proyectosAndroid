package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracion extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    RadioGroup estilo_radioGroup,orden_radioGroup;
    RadioButton estiloSeleccionado, ordenSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion_layout);

        estilo_radioGroup = findViewById(R.id.estilo_radioGroup);
        orden_radioGroup = findViewById(R.id.orden_radioGroup);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = preferences.edit();

        cargarPrefs();
    }

    public void cargarPrefs(){

        String estilo = preferences.getString("estilo","Colores cálidos");
        String orden = preferences.getString("orden","Sin orden");

        switch (estilo){
            case "Colores cálidos":

                RadioButton calidos = (RadioButton) findViewById(R.id.estiloCalido);
                calidos.setChecked(true);
                break;
            case "Colores fríos":

                RadioButton frios = (RadioButton) findViewById(R.id.estiloFrio);
                frios.setChecked(true);
                break;
            case "Colores pastel":

                RadioButton pastel = (RadioButton) findViewById(R.id.estiloPastel);
                pastel.setChecked(true);
                break;
        }

        switch (orden){
            case "Última modificación":

                RadioButton ultMod = (RadioButton) findViewById(R.id.ordenUltMod);
                ultMod.setChecked(true);
                break;
            case "Por colores":

                RadioButton porColores = (RadioButton) findViewById(R.id.ordenColores);
                porColores.setChecked(true);
                break;
            case "Alfabéticamente":

                RadioButton alfab = (RadioButton) findViewById(R.id.ordenAlfab);
                alfab.setChecked(true);
                break;
        }
    }

    public void guardar(View view){

        int idEstiloSeleccionado = estilo_radioGroup.getCheckedRadioButtonId();
        estiloSeleccionado = (RadioButton)findViewById(idEstiloSeleccionado);

        int idOrdenSeleccionado = orden_radioGroup.getCheckedRadioButtonId();
        ordenSeleccionado = (RadioButton)findViewById(idOrdenSeleccionado);

        String estilo = estiloSeleccionado.getText().toString();
        String orden = ordenSeleccionado.getText().toString();

        editor.putString("estilo", estilo);
        editor.putString("orden", orden);
        editor.commit();

        //Toast.makeText(this, estilo+", "+orden, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void acercade(View view){
        Intent intent = new Intent(this, AcercaDe.class);
        startActivity(intent);
    }

    public void ayuda(View view){
        Intent intent = new Intent(this, Ayuda.class);
        startActivity(intent);
    }

    public void atras(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) { //botón atrás del menu de navegación del movil

        if(keyCode == KeyEvent.KEYCODE_BACK){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            return true;
        }else{
            return super.onKeyUp(keyCode, event);
        }
    }
}
