package com.example.appnotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Tablero extends View implements View.OnTouchListener{

    int ancho,alto; //medidas pantalla
    int cordX,cordY; //coordenadas del click
    int numColumnas,numFilas;
    int fila,columna; //posicion de la casilla
    int unidad; //lo que mide una casilla

    public Tablero(Context context) {

        super(context);
        this.setOnTouchListener(this);

        numColumnas = 4;
        numFilas = 6;
    }

    protected void onDraw(Canvas canvas){
        Paint pincel = new Paint();
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(3);


        ancho = canvas.getWidth(); //1050
        alto = canvas.getHeight(); //1050
        unidad = ancho/numColumnas;

        canvas.drawARGB(255,255,255,255);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        cordY = (int) event.getY();
        cordX = (int) event.getX();

        fila = (cordY/(alto/numFilas));
        columna = (cordX/(ancho/numColumnas));

        Toast.makeText(getContext(), fila+","+columna, Toast.LENGTH_SHORT).show();


        invalidate();
        return true;
    }
}

/*
if(pulsada){
    pulsada = false
    iniciamos otra actividad
}
 */