package com.example.ejerciciosdibujos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = findViewById(R.id.layout);

        //Lineas pintar = new Lineas(this);
        //Circulos pintar = new Circulos(this);
        //Rectangulos pintar = new Rectangulos(this);
        //OvalosRectangulos pintar = new OvalosRectangulos(this);
        //Texto pintar = new Texto(this);
        PintaTriangulos pintar = new PintaTriangulos(this);

        layout.addView(pintar);
    }


}