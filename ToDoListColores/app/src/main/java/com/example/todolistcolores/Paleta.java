package com.example.todolistcolores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Paleta extends View implements View.OnTouchListener {

    int ancho, alto;
    int pos;
    int unidad;
    boolean[] pulsadas;

    public Paleta(Context context) {
        super(context);
        this.setOnTouchListener(this);

        pulsadas = new boolean[5];
        pos = 3;

        for(int i=0;i<pulsadas.length;i++){
            pulsadas[i]=false;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        ancho = canvas.getWidth(); //1050
        alto = canvas.getHeight(); //1050
        unidad = ancho/5;

        Paint colores = new Paint();
        colores.setARGB(255,255, 50, 0); //rojo
        canvas.drawRect(0,0,unidad,alto,colores);

        colores.setARGB(255,255, 100, 50); //naranja
        canvas.drawRect(unidad,0,unidad+unidad,alto,colores);

        colores.setARGB(255,255, 225, 0); //amarillo
        canvas.drawRect(unidad*2,0,unidad*2+unidad,alto,colores);

        colores.setARGB(255,255, 255, 255); //blanco
        canvas.drawRect(unidad*3,0,unidad*3+unidad,alto,colores);

        colores.setARGB(255,150, 150, 150); //gris
        canvas.drawRect(unidad*4,0,unidad*4+unidad,alto,colores);

        Paint bordes = new Paint();
        bordes.setARGB(255,0,0,0);
        bordes.setStyle(Paint.Style.STROKE);
        bordes.setStrokeWidth(5);
        canvas.drawRect(1, 1,ancho-1,alto-1,bordes);


        for(int i = 0; i < pulsadas.length; i++){

            canvas.drawLine(unidad*i, 0, unidad*i, alto, bordes); //linea
        }

        for(int i = 0; i < pulsadas.length; i++){

            if(pulsadas[i]){
                seleccionarColor(canvas,pos);
            }
        }


    }

    private void seleccionarColor(Canvas canvas, int pos) {

        Paint marco = new Paint();
        marco.setARGB(255,0, 200, 0); //amarillo
        marco.setStyle(Paint.Style.STROKE);
        marco.setStrokeWidth(18);
        canvas.drawRect(unidad*pos+2,2,unidad*pos+unidad-2,alto-2,marco);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        pos = (int) event.getX()/unidad;

        for(int i=0;i<pulsadas.length;i++){
            pulsadas[i]=false;
        }

        pulsadas[pos] = true;

        invalidate();
        return true;
    }
}
