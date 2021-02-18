package com.example.todolist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Paleta extends View implements View.OnTouchListener {

    int ancho, alto;
    int unidad;

    static int pos;
    boolean[] pulsadas;
    String[] colores;

    public Paleta(Context context) {
        super(context);
        this.setOnTouchListener(this);

        pos = 3;
        inicializacion();
    }

    public Paleta(Context context, int posDefault) {
        super(context);
        this.setOnTouchListener(this);

        pos = posDefault;
        inicializacion();
    }

    public void inicializacion(){
        pulsadas = new boolean[5];
        colores = new String[]{"rojo", "naranja", "amarillo", "blanco", "gris"};

        for(int i=0;i<pulsadas.length;i++){
            pulsadas[i]=false;
        }

        pulsadas[pos] = true;
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
        canvas.drawRect(1, 1,ancho-1,alto-1,bordes); //borde

        for(int i = 0; i < pulsadas.length; i++){

            canvas.drawLine(unidad*i, 0, unidad*i, alto, bordes); //lineas
        }

        marcarColor(canvas,pos);
    }

    private void marcarColor(Canvas canvas, int pos) {

        Paint marco = new Paint();
        marco.setARGB(255,0, 200, 0); //verde
        marco.setStyle(Paint.Style.STROKE);
        marco.setStrokeWidth(18);
        canvas.drawRect(unidad*pos+2,2,unidad*pos+unidad-2,alto-2,marco);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        for(int i=0;i<pulsadas.length;i++){
            pulsadas[i]=false;
        }

        pos = (int) event.getX()/unidad;
        pulsadas[pos] = true;

        invalidate();
        return true;
    }
}
