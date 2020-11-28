package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos variables
        final EditText num1 = (EditText)findViewById(R.id.num1);
        final EditText num2 = (EditText)findViewById(R.id.num2);

        final Button suma = (Button)findViewById(R.id.btnSum);
        final Button resta = (Button)findViewById(R.id.btnRes);
        final Button division = (Button)findViewById(R.id.btnDiv);
        final Button multiplicacion = (Button)findViewById(R.id.btnMult);
        final Button calcular = (Button)findViewById(R.id.calcular);


        final TextView signo = (TextView)findViewById(R.id.signo);
        final TextView resultado = (TextView)findViewById(R.id.resultado);
        final String[] signoOperacion = new String[1];

        //la suma será el valor por defecto
        signoOperacion[0] = "+";

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signo.setText("+");
                signoOperacion[0] = "+";
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signo.setText("-");
                signoOperacion[0] = "-";
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signo.setText("/");
                signoOperacion[0] = "/";
            }
        });

        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signo.setText("*");
                signoOperacion[0] = "*";
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numero1 = Integer.parseInt(num1.getText().toString());
                int numero2 = Integer.parseInt(num2.getText().toString());

                switch (signoOperacion[0]){
                    case "+":
                        resultado.setText( numero1+numero2+"" );
                        break;
                    case "-":
                        resultado.setText( numero1-numero2+"" );
                        break;
                    case "/":
                        if(numero2 == 0){
                            Toast toast = Toast.makeText(getApplicationContext(), "No puedes dividir un número entre 0", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else{
                            resultado.setText( numero1/numero2+"" );
                        }
                        break;
                    case "*":

                        resultado.setText( numero1*numero2+"" );

                        break;
                }


            }
        });


    }
}