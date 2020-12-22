package com.example.apimnimo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonSolucion = (Button)findViewById(R.id.button);
        botonSolucion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textoSol = (TextView)findViewById(R.id.textView4);
                textoSol.setText(""); //vaciamos el texto anterior al pulsar el boton

                EditText numIntroducido = (EditText)findViewById(R.id.editTextNumber);
                String numSt = numIntroducido.getText().toString();
                int num = Integer.parseInt(numSt); //cogemos el numero en num

                int i = 1;
                while(i <= num){
                    if(i%2==0) { //cada numero divisible por 2 se añade
                        textoSol.setText(textoSol.getText()+" "+i);
                    }
                    i++;
                }

            }
        });

    }
}