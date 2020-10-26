package com.example.adivinarnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final int numRandom = 40;
        final int numRandom = (int)(Math.random()*100);

        final Button boton = (Button)findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText intento = (EditText)findViewById(R.id.editTextNumber);
                int intentoNum = Integer.parseInt(intento.getText().toString());

                TextView respuesta = (TextView)findViewById(R.id.respuesta);
                if(intentoNum > numRandom){
                    respuesta.setText("Ese número es más grande!");
                }else if(intentoNum < numRandom){
                    respuesta.setText("Ese número es más pequeño!");
                }else{
                    respuesta.setText("Acertaste!!!!!!!!!!!!");
                }
            }
        });
    }
}