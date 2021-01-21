package com.example.buscaminasv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Configuracion extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion_layout);

        final EditText nBombasText = (EditText)findViewById(R.id.nBombas);
        nBombasText.setText(MenuPrincipal.bombas+"");

        Button guardar = (Button)findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int nBombas = Integer.parseInt(nBombasText.getText().toString());
                MenuPrincipal.bombas = nBombas;

                Toast toast = Toast.makeText(getApplicationContext(), "Cambios guardados", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        });

        Button atras = (Button)findViewById(R.id.volver);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MenuPrincipal.class);
                startActivity(intent);
            }
        });

    }
}
