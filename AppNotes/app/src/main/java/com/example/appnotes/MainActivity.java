package com.example.appnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        layout = findViewById(R.id.layout);
        Tablero pintarTablero = new Tablero(this);
        layout.addView(pintarTablero);
    }

    public void nuevaNota(View view){

    }

    public void salir(View view){

    }
}