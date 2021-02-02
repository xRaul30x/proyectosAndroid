package com.example.buscaminasv3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracion extends AppCompatActivity {

    String dificultad;
    EditText nBombasText;
    RadioGroup dificultades;
    TextView difActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion_layout);

        nBombasText = (EditText)findViewById(R.id.nBombas);
        dificultades = (RadioGroup)findViewById(R.id.radioDificulty);
        difActual = (TextView)findViewById(R.id.difActual);

        cargarPrefs();
    }

    public void guardarPrefs(View view){

        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);

        int nBombas = Integer.parseInt(nBombasText.getText().toString());

        int idSeleccionado = dificultades.getCheckedRadioButtonId();
        RadioButton seleccionado = (RadioButton)findViewById(idSeleccionado);
        String dificultadSeleccionada = seleccionado.getContentDescription().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nBombas",nBombas);
        editor.putString("dificultad",dificultadSeleccionada);

        editor.commit();

        Toast toast = Toast.makeText(getApplicationContext(), "Cambios guardados", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();

    }

    public void cargarPrefs(){
        SharedPreferences preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);

        int bombas = preferences.getInt("nBombas",10);
        String dificultad = preferences.getString("dificultad","normal");

        nBombasText.setText(bombas+"");
        difActual.setText(dificultad.toUpperCase());
    }

    public void irAtras(View view){

        Intent intent = new Intent(getApplicationContext(),MenuPrincipal.class);
        startActivity(intent);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.facil:
                if (checked) {

                    dificultad = "facil";
                    nBombasText.setText("4");
                }

                break;
            case R.id.normal:
                if (checked) {

                    dificultad = "normal";
                    nBombasText.setText("10");
                }

                break;
            case R.id.dificil:
                if (checked) {

                    dificultad = "dificil";
                    nBombasText.setText("16");
                }

                break;
        }

    }
}
