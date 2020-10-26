package com.example.apppulsame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button boton = (Button)findViewById(R.id.button);
        final int[] contador = {0};
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contador[0]++;
                boton.setText("PULSAME "+contador[0]+" veces");

            }
        });
    }
}